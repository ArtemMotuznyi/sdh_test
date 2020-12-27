package com.developerartemmotuznyi.sdhtest.local.datastore.model

import com.developerartemmotuznyi.sdhtest.CompositionPreferences
import com.developerartemmotuznyi.sdhtest.PackagingPreferences
import com.developerartemmotuznyi.sdhtest.VariantPreferences
import com.developerartemmotuznyi.sdhtest.domain.model.Packaging

fun PackagingPreferences.toDomain() = Packaging(
		this.id,
		this.composition.toDomain(),
		this.description,
		this.inBulk,
		this.minimumQuantity,
		this.packageQuantity,
		this.variant.toDomain()
)

fun PackagingPreferences.Builder.parse(packaging: Packaging): PackagingPreferences = with(PackagingPreferences.newBuilder()) {
	id = packaging.id
	composition = CompositionPreferences.newBuilder().parse(packaging.composition)
	description = packaging.description
	inBulk = packaging.inBulk
	minimumQuantity = packaging.minimalQuantity
	packageQuantity = packaging.packageQuantity
	variant = VariantPreferences.newBuilder().parse(packaging.variant)
	build()
}