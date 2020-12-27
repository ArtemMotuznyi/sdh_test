package com.developerartemmotuznyi.sdhtest.presentation.medicine

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.developerartemmotuznyi.sdhtest.core.model.handle
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine
import com.developerartemmotuznyi.sdhtest.domain.usecase.LoadMedicineUseCase
import com.developerartemmotuznyi.sdhtest.domain.usecase.SearchMedicineUseCase
import com.developerartemmotuznyi.sdhtest.domain.usecase.UpdateMedicineStateUseCase
import kotlinx.coroutines.launch

class MedicinesViewModel @ViewModelInject constructor(
        private val loadMedicineUseCase: LoadMedicineUseCase,
        private val searchMedicineUseCase: SearchMedicineUseCase,
        private val updateMedicineStateUseCase: UpdateMedicineStateUseCase
) : ViewModel() {

    private val _searchQuery = MutableLiveData("")
    val medicinesAll = _searchQuery.switchMap {
        loadMedicines(it)
    }

    val medicinesFavorite = _searchQuery.switchMap {
        /*loadFavorites(it)*/
        loadMedicines(it)
    }

    private val _refresh = MutableLiveData<Boolean>()
    val refresh: LiveData<Boolean> = _refresh

    private fun loadFavorites(it: String?) {

    }

    private fun loadMedicines(s: String) = Pager(
            config = PagingConfig(
                    pageSize = 55,
                    enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PagingMedicineSource(
                        s,
                        loadMedicineUseCase,
                        searchMedicineUseCase
                )
            }
    ).flow.cachedIn(viewModelScope).asLiveData()

    fun updateMedicineState(medicine: Medicine) {
        viewModelScope.launch {
            updateMedicineStateUseCase(medicine).handle({
                _refresh.postValue(true)
                _refresh.postValue(false)
            }, {
                _refresh.postValue(false)
            })
        }
    }

}
