package com.developerartemmotuznyi.sdhtest.network.source

import com.developerartemmotuznyi.sdhtest.network.api.MedicineApi
import com.developerartemmotuznyi.sdhtest.network.model.request.LoadMedicineQueryParams
import com.developerartemmotuznyi.sdhtest.network.model.request.SearchMedicineQueryParams
import javax.inject.Inject

class DefaultMedicineRemoteDataSource @Inject constructor(
    private val api: MedicineApi
) : MedicineRemoteDataSource {

    override suspend fun loadMedicine(params: LoadMedicineQueryParams) {
        api.loadMedicine(params)
    }

    override suspend fun searchMedicine(params: SearchMedicineQueryParams) {
        api.searchMedicine(params)
    }

    override suspend fun loadMedicineDetail(id: Long) {
        api.loadMedicineById(id)
    }
}