package com.developerartemmotuznyi.sdhtest.presentation.container

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.developerartemmotuznyi.sdhtest.core.model.handleWithResult
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine
import com.developerartemmotuznyi.sdhtest.domain.model.PagingResult
import com.developerartemmotuznyi.sdhtest.domain.usecase.LoadMedicineUseCase

class PagingMedicineSource(
        private val query: String,
        private val loadMedicineUseCase: LoadMedicineUseCase
) : PagingSource<Int, Medicine>() {

    override val keyReuseSupported: Boolean
        get() = true

    override fun getRefreshKey(state: PagingState<Int, Medicine>): Int? {
        val page = state.closestPageToPosition(state.anchorPosition ?: 0)
        return page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Medicine> = try {
        val nextPage = getNextKey(params.key)
        val result = loadData(nextPage)
        result.handleWithResult(::handleSuccess, ::handleError)
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    private fun handleError(throwable: Throwable): LoadResult<Int, Medicine> =
            LoadResult.Error(throwable)

    private fun handleSuccess(pagingResult: PagingResult): LoadResult<Int, Medicine> =
            LoadResult.Page(
                    pagingResult.result,
                    if (pagingResult.previous == -1) null else pagingResult.previous,
                    if (pagingResult.next == -1) null else pagingResult.next
            )

    private fun getNextKey(key: Int?): Int = if (key != null && key > 0) key else 1

    private suspend fun loadData(page: Int) = if (query.isBlank()) {
        loadMedicineUseCase(page)
    } else {
        loadMedicineUseCase(page, query)
    }

}