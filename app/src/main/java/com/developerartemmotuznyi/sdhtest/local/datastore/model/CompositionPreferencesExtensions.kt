package com.developerartemmotuznyi.sdhtest.local.datastore.model

import com.developerartemmotuznyi.sdhtest.CompositionPreferences
import com.developerartemmotuznyi.sdhtest.InternationalNonproprietaryNamePreferences
import com.developerartemmotuznyi.sdhtest.MeasurePreferences
import com.developerartemmotuznyi.sdhtest.PharmFormPreferences
import com.developerartemmotuznyi.sdhtest.domain.model.Composition

fun CompositionPreferences.Builder.parse(composition: Composition): CompositionPreferences = with(CompositionPreferences.newBuilder()) {
	id = composition.id
	description = composition.description
	addAllAtc(composition.atc)
	inn = InternationalNonproprietaryNamePreferences.newBuilder().parse(composition.inn)
	pharmForm = PharmFormPreferences.newBuilder().parse(composition.pharmForm)
	dossage = composition.dosage
	measure = MeasurePreferences.newBuilder().parse(composition.measure)
	build()
}

fun CompositionPreferences.toDomain() = Composition(
		this.id,
		this.description,
		this.atcList,
		this.inn.toDomain(),
		this.pharmForm.toDomain(),
		this.dossage,
		this.measure.toDomain()
)
