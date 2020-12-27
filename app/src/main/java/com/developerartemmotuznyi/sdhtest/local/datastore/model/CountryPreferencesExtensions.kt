package com.developerartemmotuznyi.sdhtest.local.datastore.model

import com.developerartemmotuznyi.sdhtest.CountryPreferences
import com.developerartemmotuznyi.sdhtest.domain.model.Country

fun CountryPreferences.toDomain() = Country(
		this.id,
		this.name,
		this.iso2,
		this.iso3
)

fun CountryPreferences.Builder.parse(country: Country): CountryPreferences = with(CountryPreferences.newBuilder()) {
	id = country.id
	name = country.name
	iso2 = country.iso2
	iso3 = country.iso3
	build()
}