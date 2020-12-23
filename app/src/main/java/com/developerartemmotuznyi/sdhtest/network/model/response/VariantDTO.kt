package com.developerartemmotuznyi.sdhtest.network.model.response

import kotlinx.serialization.SerialName

class VariantDTO(
    @SerialName("id")
    val id: Long?,
    @SerialName("pharm_form")
    val pharmForm: PharmFormDTO?,
    @SerialName("name")
    val name: String?,
    @SerialName("short_name")
    val shortName: String?
)