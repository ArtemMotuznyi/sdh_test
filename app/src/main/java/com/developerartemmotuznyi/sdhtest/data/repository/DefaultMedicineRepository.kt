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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultMedicineRepository @Inject constructor(
        private val remoteDataSource: MedicineRemoteDataSource,
        private val localDataSource: MedicineLocalDataSource
) : MedicineRepository {

    override suspend fun loadMedicines(page: Int, q: String): ActionResult<PagingResult> =
            withContext(Dispatchers.IO) {
                loadMedicinesByQuery(page, q)
                        .transform { it.toDomain() }
                        .join(::loadFavoritesByRemoteResult, ::mergeRemoteAndLocal)
            }

    private fun mergeRemoteAndLocal(remote: PagingResult, local: List<Medicine>): PagingResult {
        val remoteResult = remote.result

        val resultList = remoteResult.map {
            local.find { localItem -> localItem.id == it.id } ?: it
        }
        return remote.copy(result = resultList)
    }

    private suspend fun loadFavoritesByRemoteResult(remote: PagingResult) =
            localDataSource.loadMedicinesById(remote.result.map { it.id }).transform { it.map { item -> item.toDomain() } }


    private suspend fun loadMedicinesByQuery(page: Int, q: String) = if (q.isBlank()) {
        remoteDataSource.loadMedicine(LoadMedicineQueryParams.create(page))
    } else {
        remoteDataSource.searchMedicine(SearchMedicineQueryParams.create(page, q))
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

    override suspend fun loadFavorite(q: String): Flow<ActionResult<List<Medicine>>> = withContext(Dispatchers.IO) {
        localDataSource.observeMedicines(q).map { result ->
            result.transform { favorites -> favorites.map { it.toDomain() } }
        }
    }

    private suspend fun saveMedicine(medicine: Medicine): ActionResult<Unit> = localDataSource.saveMedicine(medicine)

    private suspend fun removeMedicine(medicine: Medicine): ActionResult<Unit> = localDataSource.removeMedicine(medicine)
}