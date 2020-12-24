package com.developerartemmotuznyi.sdhtest.network.model.request

class LoadMedicineQueryParams private constructor(
    private val data: Map<String, Int>
) : Map<String, Int> by data {

    companion object {
        fun create(page: Int): LoadMedicineQueryParams {
            return LoadMedicineQueryParams(
                mapOf("page" to page)
            )
        }
    }

}