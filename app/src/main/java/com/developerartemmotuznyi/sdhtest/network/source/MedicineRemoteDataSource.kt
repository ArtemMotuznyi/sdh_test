package com.developerartemmotuznyi.sdhtest.network.source

import com.developerartemmotuznyi.sdhtest.core.model.ActionResult
import com.developerartemmotuznyi.sdhtest.network.core.PagingResponse
import com.developerartemmotuznyi.sdhtest.network.model.request.LoadMedicineQueryParams
import com.developerartemmotuznyi.sdhtest.network.model.request.SearchMedicineQueryParams
import com.developerartemmotuznyi.sdhtest.network.model.response.MedicineDTO

interface MedicineRemoteDataSource {

    suspend fun loadMedicine(params: LoadMedicineQueryParams): ActionResult<PagingResponse<MedicineDTO>>

    suspend fun searchMedicine(params: SearchMedicineQueryParams): ActionResult<PagingResponse<MedicineDTO>>

    suspend fun loadMedicineDetail(id: Long): ActionResult<MedicineDTO>

}