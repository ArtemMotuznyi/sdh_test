package com.developerartemmotuznyi.sdhtest.network.model.response

import kotlinx.serialization.SerialName

class InternationalNonproprietaryNameDTO(
    @SerialName("id")
    val id: Long?,
    @SerialName("name")
    val name: String?
)
