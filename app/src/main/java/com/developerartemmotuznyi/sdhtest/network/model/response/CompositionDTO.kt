package com.developerartemmotuznyi.sdhtest.network.model.response

import com.developerartemmotuznyi.sdhtest.domain.model.Composition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CompositionDTO(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("atc")
    val atc: List<String>? = null,
    @SerialName("inn")
    val inn: InternationalNonproprietaryNameDTO? = null,
    @SerialName("pharm_form")
    val pharmForm: PharmFormDTO? = null,
    @SerialName("dosage")
    val dosage: Double? = null,
    @SerialName("measure")
    val measure: MeasureDTO? = null
)

fun CompositionDTO?.toDomain() = Composition(
    this?.id ?: -1L,
    this?.description.orEmpty(),
    this?.atc.orEmpty(),
    this?.inn.toDomain(),
    this?.pharmForm.toDomain(),
    this?.dosage ?: -1.0,
    this?.measure.toDomain()
)