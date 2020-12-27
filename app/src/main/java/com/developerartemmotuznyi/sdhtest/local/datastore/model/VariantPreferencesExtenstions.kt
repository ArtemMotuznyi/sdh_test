package com.developerartemmotuznyi.sdhtest.local.datastore.model

import com.developerartemmotuznyi.sdhtest.PharmFormPreferences
import com.developerartemmotuznyi.sdhtest.VariantPreferences
import com.developerartemmotuznyi.sdhtest.domain.model.Variant

fun VariantPreferences.toDomain() = Variant(
		this.id,
		this.pharmForm.toDomain(),
		this.name,
		this.shortName
)

fun VariantPreferences.Builder.parse(variant: Variant): VariantPreferences = with(VariantPreferences.newBuilder()) {
	id = variant.id
	pharmForm = PharmFormPreferences.newBuilder().parse(variant.pharmForm)
	name = variant.name
	shortName = variant.shortName
	build()
}