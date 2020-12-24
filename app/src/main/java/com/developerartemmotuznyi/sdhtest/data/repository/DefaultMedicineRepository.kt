package com.developerartemmotuznyi.sdhtest.data.repository

import com.developerartemmotuznyi.sdhtest.core.model.ActionResult
import com.developerartemmotuznyi.sdhtest.core.model.transform
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine
import com.developerartemmotuznyi.sdhtest.domain.model.PagingResult
import com.developerartemmotuznyi.sdhtest.network.model.request.LoadMedicineQueryParams
import com.developerartemmotuznyi.sdhtest.network.model.request.SearchMedicineQueryParams
import com.developerartemmotuznyi.sdhtest.network.model.response.toDomain
import com.developerartemmotuznyi.sdhtest.network.source.MedicineRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultMedicineRepository @Inject constructor(
    private val remoteDataSource: MedicineRemoteDataSource
) : MedicineRepository {

    override suspend fun loadMedicine(page: Int): ActionResult<PagingResult> =
        withContext(Dispatchers.IO) {
            remoteDataSource.loadMedicine(LoadMedicineQueryParams.create(page))
                .transform { result ->
                    result.toDomain()
                }
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
            remoteDataSource.loadMedicineDetail(id).transform { it.toDomain() }
        }

}