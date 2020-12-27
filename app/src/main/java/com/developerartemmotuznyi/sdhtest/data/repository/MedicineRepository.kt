package com.developerartemmotuznyi.sdhtest.data.repository

import com.developerartemmotuznyi.sdhtest.core.model.ActionResult
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine
import com.developerartemmotuznyi.sdhtest.domain.model.PagingResult
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {

	suspend fun loadMedicines(page: Int, q: String): ActionResult<PagingResult>

	suspend fun loadMedicineDetail(id: Long): ActionResult<Medicine>

	suspend fun updateMedicineState(medicine: Medicine): ActionResult<Unit>

	suspend fun loadFavorite(q: String): Flow<ActionResult<List<Medicine>>>


}