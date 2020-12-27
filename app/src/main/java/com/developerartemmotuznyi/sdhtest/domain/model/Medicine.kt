package com.developerartemmotuznyi.sdhtest.domain.model

data class Medicine(
    val id: Long,
    val composition: Composition,
    val packaging: Packaging,
    val tradeLabel: TradeLabel,
    val manufacturer: Manufacturer,
    val code: String,
    val isSaved: Boolean
)