package com.developerartemmotuznyi.sdhtest.network.model.response

import com.developerartemmotuznyi.sdhtest.domain.model.Measure
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MeasureDTO(
    @SerialName("name")
    val name: String? = null,
    @SerialName("iso")
    val iso: String? = null
)

fun MeasureDTO?.toDomain() = Measure(
    this?.name.orEmpty(),
    this?.iso.orEmpty()
)
