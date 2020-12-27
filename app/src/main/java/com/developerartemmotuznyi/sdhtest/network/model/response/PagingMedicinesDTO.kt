package com.developerartemmotuznyi.sdhtest.network.model.response

import androidx.annotation.Keep
import com.developerartemmotuznyi.sdhtest.domain.model.PagingResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
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
)

fun PagingResultResponse?.toDomain() = PagingResult(
        this?.count ?: 0,
        this?.next.orEmpty(),
        this?.previous.orEmpty(),
        this?.results?.map { it.toDomain() }.orEmpty()
)

