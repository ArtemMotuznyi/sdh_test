package com.developerartemmotuznyi.sdhtest.network.model.response

import com.developerartemmotuznyi.sdhtest.domain.model.Country
import kotlinx.serialization.SerialName

class CountryDTO(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("iso2")
    val iso2: String? = null,
    @SerialName("iso3")
    val iso3: String? = null
)

fun CountryDTO?.toDomain() = Country(
    this?.id ?: -1L,
    this?.name.orEmpty(),
    this?.iso2.orEmpty(),
    this?.iso3.orEmpty()
)