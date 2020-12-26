package com.developerartemmotuznyi.sdhtest.presentation.medicine

import androidx.paging.PagingSource
import com.developerartemmotuznyi.sdhtest.core.model.handleWithResult
import com.developerartemmotuznyi.sdhtest.local.model.MedicineEntity
import com.developerartemmotuznyi.sdhtest.domain.model.PagingResult
import com.developerartemmotuznyi.sdhtest.domain.usecase.LoadMedicineUseCase
import com.developerartemmotuznyi.sdhtest.domain.usecase.SearchMedicineUseCase

class PagingMedicineSource(
    private val query: String,
    private val loadMedicineUseCase: LoadMedicineUseCase,
    private val searchMedicineUseCase: SearchMedicineUseCase
) : PagingSource<Int, MedicineEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MedicineEntity> = try {
        val nextPage = getNextKey(params.key)
        val result = loadData(nextPage)
        result.handleWithResult(::handleSuccess, ::handleError)
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    private fun handleError(throwable: Throwable): LoadResult<Int, MedicineEntity> =
        LoadResult.Error(throwable)

    private fun handleSuccess(pagingResult: PagingResult): LoadResult<Int, MedicineEntity> =
        LoadResult.Page(
            pagingResult.result,
            if (pagingResult.previousPageIndex == -1) null else pagingResult.previousPageIndex,
            if (pagingResult.nextPageIndex == -1) null else pagingResult.nextPageIndex
        )

    private fun getNextKey(key: Int?): Int = if (key != null && key > 0) key else 1

    private suspend fun loadData(page: Int) = if (query.isBlank()) {
        loadMedicineUseCase(page)
    } else {
        searchMedicineUseCase(page, query)
    }

}