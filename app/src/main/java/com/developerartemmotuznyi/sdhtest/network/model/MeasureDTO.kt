package com.developerartemmotuznyi.sdhtest.network.model

import kotlinx.serialization.SerialName

class MeasureDTO(
    @SerialName("name")
    val name: String?,
    @SerialName("iso")
    val iso: String?
)