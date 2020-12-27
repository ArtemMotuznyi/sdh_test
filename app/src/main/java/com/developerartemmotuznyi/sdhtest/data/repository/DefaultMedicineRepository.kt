package com.developerartemmotuznyi.sdhtest.data.repository

import com.developerartemmotuznyi.sdhtest.core.model.ActionResult
import com.developerartemmotuznyi.sdhtest.core.model.doOnError
import com.developerartemmotuznyi.sdhtest.core.model.join
import com.developerartemmotuznyi.sdhtest.core.model.transform
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine
import com.developerartemmotuznyi.sdhtest.domain.model.PagingResult
import com.developerartemmotuznyi.sdhtest.local.datastore.model.toDomain
import com.developerartemmotuznyi.sdhtest.local.datastore.source.MedicineLocalDataSource
import com.developerartemmotuznyi.sdhtest.local.exeptions.MedicineNotFoundException
import com.developerartemmotuznyi.sdhtest.network.model.request.LoadMedicineQueryParams
import com.developerartemmotuznyi.sdhtest.network.model.request.SearchMedicineQueryParams
import com.developerartemmotuznyi.sdhtest.network.model.response.toDomain
import com.developerartemmotuznyi.sdhtest.network.source.MedicineRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultMedicineRepository @Inject constructor(
        private val remoteDataSource: MedicineRemoteDataSource,
        private val localDataSource: MedicineLocalDataSource
) : MedicineRepository {

    override suspend fun loadMedicine(page: Int): ActionResult<PagingResult> =
        withContext(Dispatchers.IO) {
            remoteDataSource.loadMedicine(LoadMedicineQueryParams.create(page))
                    .transform { it.toDomain() }
                    .join({ remote ->
                        localDataSource.loadMedicinesById(remote.result.map { item -> item.id }).transform {
                            it.map { item -> item.toDomain() }
                        }
                    }, { remote, local ->
                        val resultList = remote.result.map {
                            local.find { localItem -> localItem.id == it.id } ?: it
                        }
                        remote.copy(result = resultList)
                    })

        }

    override suspend fun searchMedicine(
            page: Int,
            q: String
    ): ActionResult<PagingResult> = withContext(Dispatchers.IO) {
        remoteDataSource.searchMedicine(SearchMedicineQueryParams.create(page, q))
                .transform { result -> result.toDomain() }
    }

    override suspend fun loadMedicineDetail(id: Long): ActionResult<Medicine> =
            withContext(Dispatchers.IO) {
                localDataSource.loadMedicineById(id)
                        .transform { it.toDomain() }
                        .doOnError {
                            if (it is MedicineNotFoundException) {
                                remoteDataSource.loadMedicineDetail(id)
                            } else {
                                ActionResult.Error(it)
                            }.transform { medicine -> medicine.toDomain() }
                        }
            }

    override suspend fun updateMedicineState(medicine: Medicine): ActionResult<Unit> = withContext(Dispatchers.IO) {
        if (medicine.isSaved) removeMedicine(medicine) else saveMedicine(medicine)
    }

    private suspend fun saveMedicine(medicine: Medicine): ActionResult<Unit> = localDataSource.saveMedicine(medicine)

    private suspend fun removeMedicine(medicine: Medicine): ActionResult<Unit> = localDataSource.removeMedicine(medicine)
}