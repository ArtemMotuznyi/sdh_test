package com.developerartemmotuznyi.sdhtest.network.model

import kotlinx.serialization.SerialName

class PharmFormDTO(
    @SerialName("id")
    val id: Long?,
    @SerialName("name")
    val name: String?,
    @SerialName("short_name")
    val shortName: String?
)