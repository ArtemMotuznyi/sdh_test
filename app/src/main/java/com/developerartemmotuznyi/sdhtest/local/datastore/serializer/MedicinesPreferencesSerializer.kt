package com.developerartemmotuznyi.sdhtest.local.datastore.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.developerartemmotuznyi.sdhtest.*
import com.developerartemmotuznyi.sdhtest.domain.model.*
import com.developerartemmotuznyi.sdhtest.local.datastore.model.parse
import com.developerartemmotuznyi.sdhtest.local.datastore.model.toDomain
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object MedicinesPreferencesSerializer : Serializer<MedicinesPreferences> {

	override fun readFrom(input: InputStream): MedicinesPreferences {
		try {
			return MedicinesPreferences.parseFrom(input)
		} catch (exception: InvalidProtocolBufferException) {
			throw CorruptionException("Cannot read proto.", exception)
		}
	}

	override fun writeTo(t: MedicinesPreferences, output: OutputStream) = t.writeTo(output)

	override val defaultValue: MedicinesPreferences
		get() = MedicinesPreferences.getDefaultInstance()
}