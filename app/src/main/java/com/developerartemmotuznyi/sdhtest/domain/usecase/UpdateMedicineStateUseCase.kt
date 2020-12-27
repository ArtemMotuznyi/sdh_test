package com.developerartemmotuznyi.sdhtest.domain.usecase

import com.developerartemmotuznyi.sdhtest.data.repository.MedicineRepository
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine
import javax.inject.Inject

class UpdateMedicineStateUseCase @Inject constructor(
		private val repository: MedicineRepository
) {

	suspend operator fun invoke(medicine: Medicine) = repository.updateMedicineState(medicine)

}
