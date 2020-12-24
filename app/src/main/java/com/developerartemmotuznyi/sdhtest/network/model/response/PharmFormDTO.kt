package com.developerartemmotuznyi.sdhtest.network.model.response

import com.developerartemmotuznyi.sdhtest.domain.model.PharmForm
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PharmFormDTO(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("short_name")
    val shortName: String? = null
)

fun PharmFormDTO?.toDomain() = PharmForm(
    this?.id ?: -1,
    this?.name.orEmpty(),
    this?.shortName.orEmpty()
)
