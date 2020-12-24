package com.developerartemmotuznyi.sdhtest.domain.usecase

import com.developerartemmotuznyi.sdhtest.data.repository.MedicineRepository
import javax.inject.Inject

class SearchMedicineUseCase @Inject constructor(
    private val repository: MedicineRepository
) {

    suspend operator fun invoke(page: Int, q: String) = repository.searchMedicine(page, q)

}