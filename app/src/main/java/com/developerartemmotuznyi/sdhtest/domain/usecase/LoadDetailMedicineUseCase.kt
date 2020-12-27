package com.developerartemmotuznyi.sdhtest.domain.usecase

import com.developerartemmotuznyi.sdhtest.data.repository.MedicineRepository
import javax.inject.Inject

class LoadDetailMedicineUseCase @Inject constructor(
		private val medicineRepository: MedicineRepository
) {

	suspend operator fun invoke(id: Long) = medicineRepository.loadMedicineDetail(id)

}
