package com.developerartemmotuznyi.sdhtest.network.model.response

import com.developerartemmotuznyi.sdhtest.domain.model.Variant
import kotlinx.serialization.SerialName

class VariantDTO(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("pharm_form")
    val pharmForm: PharmFormDTO? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("short_name")
    val shortName: String? = null
)

fun VariantDTO?.toDomain() = Variant(
    this?.id ?: -1,
    this?.pharmForm.toDomain(),
    this?.name.orEmpty(),
    this?.shortName.orEmpty()
)
