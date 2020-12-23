package com.developerartemmotuznyi.sdhtest.network.model

import kotlinx.serialization.SerialName

class PackagingDTO(
    @SerialName("id")
    val id: Long?,
    @SerialName("composition")
    val composition: CompositionDTO,
    @SerialName("description")
    val description: String?,
    @SerialName("in_bulk")
    val inBulk: Boolean?,
    @SerialName("minimal_quantity")
    val minimalQuantity: Double?,
    @SerialName("package_quantity")
    val packageQuantity: Double?,
    @SerialName("variant")
    val variant: VariantDTO?
)