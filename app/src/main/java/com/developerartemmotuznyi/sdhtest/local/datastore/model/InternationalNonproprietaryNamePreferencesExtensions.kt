package com.developerartemmotuznyi.sdhtest.local.datastore.model

import com.developerartemmotuznyi.sdhtest.InternationalNonproprietaryNamePreferences
import com.developerartemmotuznyi.sdhtest.domain.model.InternationalNonproprietaryName

fun InternationalNonproprietaryNamePreferences.Builder.parse(
		inn: InternationalNonproprietaryName
): InternationalNonproprietaryNamePreferences = with(InternationalNonproprietaryNamePreferences.newBuilder()) {
	id = inn.id
	name = inn.name
	build()
}

fun InternationalNonproprietaryNamePreferences.toDomain() = InternationalNonproprietaryName(
		this.id,
		this.name
)