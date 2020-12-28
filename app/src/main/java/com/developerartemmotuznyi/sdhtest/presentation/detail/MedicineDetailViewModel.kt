package com.developerartemmotuznyi.sdhtest.presentation.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine
import com.developerartemmotuznyi.sdhtest.domain.usecase.LoadDetailMedicineUseCase
import com.developerartemmotuznyi.sdhtest.domain.usecase.UpdateMedicineStateUseCase
import com.developerartemmotuznyi.sdhtest.presentation.base.BaseViewModel

class MedicineDetailViewModel @ViewModelInject constructor(
		private val loadDetailMedicineUseCase: LoadDetailMedicineUseCase,
		private val updateMedicineStateUseCase: UpdateMedicineStateUseCase
) : BaseViewModel() {

	private val _medicine = MutableLiveData<Medicine>()
	val medicine: LiveData<Medicine> = _medicine

	fun loadMedicine(id: Long) {
		launch({ loadDetailMedicineUseCase(id) }, {
			_medicine.postValue(it)
		})
	}

	fun updateMedicineState() {
		medicine.value?.let { value ->
			launch({ updateMedicineStateUseCase(value) }, {
				_medicine.postValue(value.copy(isSaved = !value.isSaved))
			})
		}
	}

}