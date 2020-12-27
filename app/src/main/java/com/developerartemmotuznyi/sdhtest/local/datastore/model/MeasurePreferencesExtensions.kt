package com.developerartemmotuznyi.sdhtest.local.datastore.model

import com.developerartemmotuznyi.sdhtest.MeasurePreferences
import com.developerartemmotuznyi.sdhtest.domain.model.Measure


fun MeasurePreferences.toDomain() = Measure(
		this.name,
		this.iso
)

fun MeasurePreferences.Builder.parse(measure: Measure): MeasurePreferences = with(MeasurePreferences.newBuilder()) {
	name = measure.name
	iso = measure.iso
	build()
}