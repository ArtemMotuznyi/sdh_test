package com.developerartemmotuznyi.sdhtest.domain.usecase

import com.developerartemmotuznyi.sdhtest.data.repository.MedicineRepository
import javax.inject.Inject

class LoadMedicineDetailUseCase @Inject constructor(
    private val repository: MedicineRepository
) {

    suspend operator fun invoke(id: Long) = repository.loadMedicineDetail(id)

}