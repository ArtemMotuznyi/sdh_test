package com.developerartemmotuznyi.sdhtest.local.datastore.source

import androidx.datastore.core.DataStore
import com.developerartemmotuznyi.sdhtest.MedicinePreferences
import com.developerartemmotuznyi.sdhtest.MedicinesPreferences
import com.developerartemmotuznyi.sdhtest.core.model.ActionResult
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine
import com.developerartemmotuznyi.sdhtest.local.datastore.model.parse
import com.developerartemmotuznyi.sdhtest.local.exeptions.MedicineNotFoundException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

//TODO Change on Room
@Singleton
class DefaultMedicineLocalDataSource @Inject constructor(
		private val dataStore: DataStore<MedicinesPreferences>
) : MedicineLocalDataSource {

	override suspend fun saveMedicine(medicine: Medicine): ActionResult<Unit> = try {
		dataStore.updateData {
			val medicinePref = MedicinePreferences.newBuilder().parse(medicine)
			it.toBuilder().addMedicines(medicinePref).build()
		}
		ActionResult.Success(Unit)
	} catch (e: Exception) {
		ActionResult.Error(Exception("Error:save medicine", e))
	}

	override suspend fun removeMedicine(medicine: Medicine): ActionResult<Unit> = try {
		dataStore.updateData {
			val builder = it.toBuilder()
			val index = builder.medicinesList.indexOfFirst { item -> item.id == medicine.id }
			if (index != -1) {
				builder.removeMedicines(index)
			}
			builder.build()
		}
		ActionResult.Success(Unit)
	} catch (e: Exception) {
		ActionResult.Error(Exception("Error:save medicine", e))
	}

	override suspend fun loadMedicinesById(medicineIds: List<Long>): ActionResult<List<MedicinePreferences>> = try {
		val medicines = dataStore.data.firstOrNull()?.medicinesList?.filter { medicineIds.contains(it.id) }.orEmpty()
		ActionResult.Success(medicines)
	} catch (e: Exception) {
		ActionResult.Error(Exception("Error: Load Medicines", e))
	}

	override suspend fun loadMedicineById(id: Long): ActionResult<MedicinePreferences> = try {
		dataStore.data.firstOrNull()?.medicinesList?.find { id == it.id }?.let { item ->
			ActionResult.Success(item)
		} ?: ActionResult.Error(MedicineNotFoundException())

	} catch (e: Exception) {
		ActionResult.Error(Exception("Error: Load Medicine", e))
	}

	override suspend fun observeMedicines(q: String): Flow<ActionResult<List<MedicinePreferences>>> =
			dataStore.data.map {
				val result = if (q.isBlank()) it.medicinesList else it.medicinesList.filter { item -> item.equals(q) }
				ActionResult.Success(result)
			}
}