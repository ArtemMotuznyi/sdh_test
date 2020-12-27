package com.developerartemmotuznyi.sdhtest.network.model.response

import com.developerartemmotuznyi.sdhtest.domain.model.PagingResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.regex.Pattern

private const val PATTERN_PAGE = "\\bpage=[\\d]*\\b"
private const val PATTERN_PAGE_INDEX = "\\d+"


@Serializable
class PagingResultResponse(
        @SerialName("count")
        val count: Long? = null,
        @SerialName("next")
        val next: String? = null,
        @SerialName("previous")
        val previous: String? = null,
        @SerialName("results")
        val results: List<MedicineDTO>? = null
) {

    val nextPageIndex: Int
        get() = next?.let(::getPageIndex) ?: -1

    val previousPageIndex: Int
        get() = previous?.let(::getPageIndex) ?: -1

    private fun getPageIndex(path: String): Int {
        val pagePattern = Pattern.compile(PATTERN_PAGE)
        val page = pagePattern.matcher(path)
        return if (page.find()) {
            val indexPattern = Pattern.compile(PATTERN_PAGE_INDEX)
            indexPattern.matcher(page.group()).takeIf { it.find() }?.group()?.toIntOrNull() ?: -1
        } else -1
    }


}

fun PagingResultResponse?.toDomain() = PagingResult(
        this?.count ?: 0,
        this?.nextPageIndex ?: -1,
        this?.previousPageIndex ?: -1,
        this?.results?.map { it.toDomain() }.orEmpty()
)

