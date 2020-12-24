package com.developerartemmotuznyi.sdhtest.network.model.response

import com.developerartemmotuznyi.sdhtest.domain.model.InternationalNonproprietaryName
import kotlinx.serialization.SerialName

class InternationalNonproprietaryNameDTO(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("name")
    val name: String? = null
)

fun InternationalNonproprietaryNameDTO?.toDomain() = InternationalNonproprietaryName(
    this?.id ?: -1,
    this?.name.orEmpty()
)
