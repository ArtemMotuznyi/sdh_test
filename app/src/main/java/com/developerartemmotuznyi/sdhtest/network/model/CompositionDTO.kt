package com.developerartemmotuznyi.sdhtest.network.model

import kotlinx.serialization.SerialName

class CompositionDTO(
    @SerialName("id")
    val id: Long?,
    @SerialName("description")
    val description: String?,
    @SerialName("atc")
    val atc: List<String>?,
    @SerialName("inn")
    val inn: InternationalNonproprietaryNameDTO?,
    @SerialName("pharm_form")
    val pharmForm: PharmFormDTO,
    @SerialName("dosage")
    val dosage: Long,
    @SerialName("measure")
    val measure: MeasureDTO
)