package com.developerartemmotuznyi.sdhtest.network.model.response

import com.developerartemmotuznyi.sdhtest.domain.model.Medicine
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class MedicineDTO(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("composition")
    val composition: CompositionDTO? = null,
    @SerialName("packaging")
    val packaging: PackagingDTO? = null,
    @SerialName("trade_label")
    val tradeLabel: TradeLabelDTO? = null,
    @SerialName("manufacturer")
    val manufacturer: ManufacturerDTO? = null,
    @SerialName("code")
    val code: String? = null
)

fun MedicineDTO?.toDomain() = Medicine(
    this?.id ?: -1L,
    this?.composition.toDomain(),
    this?.packaging.toDomain(),
    this?.tradeLabel.toDomain(),
    this?.manufacturer.toDomain(),
    this?.code.orEmpty()
)
