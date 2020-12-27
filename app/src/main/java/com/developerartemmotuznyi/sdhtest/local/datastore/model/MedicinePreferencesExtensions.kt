package com.developerartemmotuznyi.sdhtest.local.datastore.model

import com.developerartemmotuznyi.sdhtest.*
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine

fun MedicinePreferences.Builder.parse(medicine: Medicine): MedicinePreferences = with(MedicinePreferences.newBuilder()) {
	id = medicine.id
	composition = CompositionPreferences.newBuilder().parse(medicine.composition)
	packaging = PackagingPreferences.newBuilder().parse(medicine.packaging)
	tradeLabel = TradeLabelPreferences.newBuilder().parse(medicine.tradeLabel)
	manufacturer = ManufacturerPreferences.newBuilder().parse(medicine.manufacturer)
	code = medicine.code
	build()
}

fun MedicinePreferences.toDomain() = Medicine(
		this.id,
		this.composition.toDomain(),
		this.packaging.toDomain(),
		this.tradeLabel.toDomain(),
		this.manufacturer.toDomain(),
		this.code.orEmpty(),
		true
)