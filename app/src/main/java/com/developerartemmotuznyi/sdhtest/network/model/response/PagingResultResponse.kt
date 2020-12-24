package com.developerartemmotuznyi.sdhtest.network.model.response

import com.developerartemmotuznyi.sdhtest.R
import com.developerartemmotuznyi.sdhtest.domain.model.PagingResult
import kotlinx.serialization.SerialName

class PagingResultResponse<T>(
    @SerialName("count")
    val count: Long? = null,
    @SerialName("next")
    val next: String? = null,
    @SerialName("previous")
    val previous: String? = null,
    @SerialName("result")
    val result: List<T>? = null
)

fun <T, D> PagingResultResponse<T>?.toDomain(
    toDomain: (T) -> D
) = PagingResult(
    this?.count ?: 0,
    this?.next.orEmpty(),
    this?.previous.orEmpty(),
    this?.result?.map(toDomain).orEmpty()
)

