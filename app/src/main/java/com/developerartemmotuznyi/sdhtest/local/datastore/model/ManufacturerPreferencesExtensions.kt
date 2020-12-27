package com.developerartemmotuznyi.sdhtest.local.datastore.model

import com.developerartemmotuznyi.sdhtest.CountryPreferences
import com.developerartemmotuznyi.sdhtest.ManufacturerPreferences
import com.developerartemmotuznyi.sdhtest.domain.model.Manufacturer

fun ManufacturerPreferences.toDomain() = Manufacturer(
		this.id,
		this.name,
		this.country.toDomain()
)

fun ManufacturerPreferences.Builder.parse(manufacturer: Manufacturer): ManufacturerPreferences = with(ManufacturerPreferences.newBuilder()) {
	id = manufacturer.id
	name = manufacturer.name
	country = CountryPreferences.newBuilder().parse(manufacturer.country)
	build()
}
