package com.developerartemmotuznyi.sdhtest.data.repository

import com.developerartemmotuznyi.sdhtest.core.model.ActionResult
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine
import com.developerartemmotuznyi.sdhtest.domain.model.PagingResult

interface MedicineRepository {

    suspend fun loadMedicine(page: Int): ActionResult<PagingResult<Medicine>>

    suspend fun searchMedicine(
        page: Int,
        q: String
    ): ActionResult<PagingResult<Medicine>>

    suspend fun loadMedicineDetail(id: Long): ActionResult<Medicine>

}