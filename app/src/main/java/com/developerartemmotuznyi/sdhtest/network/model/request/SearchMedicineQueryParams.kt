package com.developerartemmotuznyi.sdhtest.network.model.request

class SearchMedicineQueryParams private constructor(
    private val data: Map<String, Any>
) : Map<String, Any> by data {

    companion object {
        fun create(page: Int, query: String): SearchMedicineQueryParams {
            return SearchMedicineQueryParams(
                mapOf(
                    "page" to page,
                    "search" to query
                )
            )
        }
    }

}