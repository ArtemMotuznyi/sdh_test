package com.developerartemmotuznyi.sdhtest.network.api

import com.developerartemmotuznyi.sdhtest.network.core.PagingResponse
import com.developerartemmotuznyi.sdhtest.network.model.request.LoadMedicineQueryParams
import com.developerartemmotuznyi.sdhtest.network.model.request.SearchMedicineQueryParams
import com.developerartemmotuznyi.sdhtest.network.model.response.MedicineDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MedicineApi : BaseApi {

    @GET("v1/medicine/")
    suspend fun loadMedicine(@QueryMap params: LoadMedicineQueryParams): PagingResponse<MedicineDTO>

    @GET("v1/medicine/")
    suspend fun searchMedicine(@QueryMap params: SearchMedicineQueryParams): PagingResponse<MedicineDTO>

    @GET("v1/medicine/{id}")
    suspend fun loadMedicineById(@Path("id") id: Long): MedicineDTO

}