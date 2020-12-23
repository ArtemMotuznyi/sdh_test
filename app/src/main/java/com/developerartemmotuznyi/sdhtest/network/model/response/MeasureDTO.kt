package com.developerartemmotuznyi.sdhtest.network.model.response

import kotlinx.serialization.SerialName

class MeasureDTO(
    @SerialName("name")
    val name: String?,
    @SerialName("iso")
    val iso: String?
)