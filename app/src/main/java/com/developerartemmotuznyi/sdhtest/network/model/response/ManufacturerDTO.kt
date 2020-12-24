package com.developerartemmotuznyi.sdhtest.network.model.response

import com.developerartemmotuznyi.sdhtest.domain.model.Manufacturer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ManufacturerDTO(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("country")
    val country: CountryDTO? = null
)

fun ManufacturerDTO?.toDomain() = Manufacturer(
    this?.id ?: -1L,
    this?.name.orEmpty(),
    this?.country.toDomain()
)