package com.developerartemmotuznyi.sdhtest.domain.model

data class Packaging(
    val id: Long,
    val composition: Composition,
    val description: String,
    val inBulk: Boolean,
    val minimalQuantity: Double,
    val packageQuantity: Double,
    val variant: Variant
)