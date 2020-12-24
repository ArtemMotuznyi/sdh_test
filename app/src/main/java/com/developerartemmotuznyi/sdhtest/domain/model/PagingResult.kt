package com.developerartemmotuznyi.sdhtest.domain.model

import java.util.regex.Pattern

data class PagingResult(
    val count: Long,
    val next: String,
    val previous: String,
    val result: List<Medicine>
) {

    val nextPageIndex: Int
        get() = getPageIndex(next)

    val previousPageIndex: Int
        get() = getPageIndex(previous)

    private fun getPageIndex(page: String): Int {
        val pattern = Pattern.compile("(\\\\d+)")


        val startIndex = page.indexOf("page=")
        val endIndex = page.lastIndexOf("&")

        return if (startIndex != -1) {
            page.substring(startIndex + 5, if (endIndex != -1) endIndex else page.length)
                .toIntOrNull() ?: -1
        } else -1
    }

}