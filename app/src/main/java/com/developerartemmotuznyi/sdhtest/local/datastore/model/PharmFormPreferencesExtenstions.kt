package com.developerartemmotuznyi.sdhtest.local.datastore.model

import com.developerartemmotuznyi.sdhtest.PharmFormPreferences
import com.developerartemmotuznyi.sdhtest.domain.model.PharmForm

fun PharmFormPreferences.Builder.parse(pharmForm: PharmForm): PharmFormPreferences = with(PharmFormPreferences.newBuilder()) {
	id = pharmForm.id
	name = pharmForm.name
	shortName = pharmForm.shortName
	build()
}

fun PharmFormPreferences.toDomain() = PharmForm(
		this.id,
		this.name,
		this.shortName
)
