package com.developerartemmotuznyi.sdhtest.domain.model

data class PagingResult<T>(
    val count: Long,
    val next: String,
    val previous: String,
    val result: List<T>
) {

    val nextPageIndex: Int
        get() = getPageIndex(next)

    val previousPageIndex: Int
        get() = getPageIndex(previous)

    private fun getPageIndex(page: String): Int {
        val startIndex = page.indexOf("page=")
        val endIndex = page.lastIndexOf("&")

        return if (startIndex != -1) {
            page.substring(startIndex, if (endIndex != -1) endIndex else page.lastIndex)
                .toIntOrNull() ?: -1
        } else -1
    }

}