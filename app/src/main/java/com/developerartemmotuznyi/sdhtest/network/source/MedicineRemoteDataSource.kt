package com.developerartemmotuznyi.sdhtest.network.source

import com.developerartemmotuznyi.sdhtest.core.model.ActionResult
import com.developerartemmotuznyi.sdhtest.network.model.request.LoadMedicineQueryParams
import com.developerartemmotuznyi.sdhtest.network.model.request.SearchMedicineQueryParams
import com.developerartemmotuznyi.sdhtest.network.model.response.MedicineDTO
import com.developerartemmotuznyi.sdhtest.network.model.response.PagingResultResponse

interface MedicineRemoteDataSource {

    suspend fun loadMedicine(params: LoadMedicineQueryParams): ActionResult<PagingResultResponse>

    suspend fun searchMedicine(params: SearchMedicineQueryParams): ActionResult<PagingResultResponse>

    suspend fun loadMedicineDetail(id: Long): ActionResult<MedicineDTO>

}