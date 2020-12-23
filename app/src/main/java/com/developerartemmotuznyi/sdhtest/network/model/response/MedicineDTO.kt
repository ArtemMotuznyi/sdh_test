package com.developerartemmotuznyi.sdhtest.network.model.response

import kotlinx.serialization.SerialName

class MedicineDTO(
    @SerialName("id")
        val id: Long?,
    @SerialName("composition")
        val composition: CompositionDTO,
    @SerialName("packaging")
        val packaging: PackagingDTO,
    @SerialName("trade_label")
        val tradeLabel: TradeLabelDTO,
    @SerialName("manufacturer")
        val manufacturer: ManufacturerDTO,
    @SerialName("code")
        val code: String
)