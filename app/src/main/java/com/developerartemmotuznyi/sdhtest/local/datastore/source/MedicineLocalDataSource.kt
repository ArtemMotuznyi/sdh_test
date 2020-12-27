package com.developerartemmotuznyi.sdhtest.local.datastore.source

import com.developerartemmotuznyi.sdhtest.MedicinePreferences
import com.developerartemmotuznyi.sdhtest.core.model.ActionResult
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine
import kotlinx.coroutines.flow.Flow

interface MedicineLocalDataSource {

	suspend fun saveMedicine(medicine: Medicine): ActionResult<Unit>

	suspend fun removeMedicine(medicine: Medicine): ActionResult<Unit>

	suspend fun loadMedicinesById(medicineIds: List<Long>): ActionResult<List<MedicinePreferences>>

	suspend fun loadMedicineById(id: Long): ActionResult<MedicinePreferences>

	suspend fun observeMedicines(q: String): Flow<ActionResult<List<MedicinePreferences>>>

}