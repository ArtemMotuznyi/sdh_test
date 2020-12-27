package com.developerartemmotuznyi.sdhtest.domain.usecase

import com.developerartemmotuznyi.sdhtest.data.repository.MedicineRepository
import javax.inject.Inject

class FavoriteMedicineUseCase @Inject constructor(
		private val medicineRepository: MedicineRepository
) {

	suspend operator fun invoke(q: String = "") = medicineRepository.loadFavorite(q)

}
