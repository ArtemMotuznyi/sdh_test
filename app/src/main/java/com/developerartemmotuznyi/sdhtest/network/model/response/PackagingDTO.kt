package com.developerartemmotuznyi.sdhtest.network.model.response

import com.developerartemmotuznyi.sdhtest.domain.model.Packaging
import kotlinx.serialization.SerialName

class PackagingDTO(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("composition")
    val composition: CompositionDTO? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("in_bulk")
    val inBulk: Boolean? = null,
    @SerialName("minimal_quantity")
    val minimalQuantity: Double? = null,
    @SerialName("package_quantity")
    val packageQuantity: Double? = null,
    @SerialName("variant")
    val variant: VariantDTO? = null
)

fun PackagingDTO?.toDomain() = Packaging(
    this?.id ?: -1L,
    this?.composition.toDomain(),
    this?.description.orEmpty(),
    this?.inBulk ?: false,
    this?.minimalQuantity ?: 0.0,
    this?.packageQuantity ?: 0.0,
    this?.variant.toDomain()
)
