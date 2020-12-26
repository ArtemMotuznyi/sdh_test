package com.developerartemmotuznyi.sdhtest.local.datastore.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.developerartemmotuznyi.sdhtest.*
import com.developerartemmotuznyi.sdhtest.domain.model.*
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object MedicinePreferencesSerializer : Serializer<MedicinePreferences> {

	override fun readFrom(input: InputStream): MedicinePreferences {
		try {
			return MedicinePreferences.parseFrom(input)
		} catch (exception: InvalidProtocolBufferException) {
			throw CorruptionException("Cannot read proto.", exception)
		}
	}

	override fun writeTo(t: MedicinePreferences, output: OutputStream) = t.writeTo(output)

	override val defaultValue: MedicinePreferences
		get() = MedicinePreferences.getDefaultInstance()
}

fun MedicinePreferences.toDomain() = Medicine(
		this.id,
		this.composition.toDomain(),
		this.packaging.toDomain(),
		this.tradeLabel.toDomain(),
		this.manufacturer.toDomain(),
		this.code.orEmpty()
)

fun CompositionPreferences.toDomain() = Composition(
		this.id,
		this.description,
		this.atcList,
		this.inn.toDomain(),
		this.pharmFrom.toDomain(),
		this.dossage,
		this.measure.toDomain()
)

fun InternationalNonproprietaryNamePreferences.toDomain() = InternationalNonproprietaryName(
		this.id,
		this.name
)

fun PharmFromPreferences.toDomain() = PharmForm(
		this.id,
		this.name,
		this.shortName
)

fun MeasurePreferences.toDomain() = Measure(
		this.name,
		this.iso
)

fun PackagingPreferences.toDomain() = Packaging(
		this.id,
		this.composition.toDomain(),
		this.description,
		this.inBulk,
		this.minimumQuantity,
		this.packageQuantity,
		this.variant.toDomain()

)

fun TradeLabelPreferences.toDomain() = TradeLabel(
		this.id,
		this.name
)

fun ManufacturerPreferences.toDomain() = Manufacturer(
		this.id,
		this.name,
		this.country.toDomain()
)

fun VariantPreferences.toDomain() = Variant(
		this.id,
		this.pharmFrom.toDomain(),
		this.name,
		this.shortName
)

fun CountryPreferences.toDomain() = Country(
		this.id,
		this.name,
		this.iso2,
		this.iso3
)