package com.developerartemmotuznyi.sdhtest.presentation.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerartemmotuznyi.sdhtest.core.model.handle
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine
import com.developerartemmotuznyi.sdhtest.domain.usecase.LoadDetailMedicineUseCase
import kotlinx.coroutines.launch

class MedicineDetailViewModel @ViewModelInject constructor(
		private val loadDetailMedicineUseCase: LoadDetailMedicineUseCase
) : ViewModel() {

	private val _medicine = MutableLiveData<Medicine>()
	val medicine: LiveData<Medicine> = _medicine

	fun loadMedicine(id: Long) {
		viewModelScope.launch {
			loadDetailMedicineUseCase(id).handle({
				_medicine.postValue(it)
			}, {})
		}
	}

}