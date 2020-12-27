package com.developerartemmotuznyi.sdhtest.presentation.medicinecontainer

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.developerartemmotuznyi.sdhtest.core.model.handle
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine
import com.developerartemmotuznyi.sdhtest.domain.usecase.FavoriteMedicineUseCase
import com.developerartemmotuznyi.sdhtest.domain.usecase.LoadMedicineUseCase
import com.developerartemmotuznyi.sdhtest.domain.usecase.UpdateMedicineStateUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MedicinesViewModel @ViewModelInject constructor(
        private val loadMedicineUseCase: LoadMedicineUseCase,
        private val loadFavoriteMedicineUseCase: FavoriteMedicineUseCase,
        private val updateMedicineStateUseCase: UpdateMedicineStateUseCase
) : ViewModel() {

    private val _searchQuery = MutableLiveData("")
    val medicinesAll = _searchQuery.switchMap {
        loadMedicines(it)
    }

    val medicinesFavorite = _searchQuery.switchMap {
        liveData<List<Medicine>> {
            loadFavoriteMedicineUseCase(it).collect { result ->
                result.handle({ emit(it) }, { emit(emptyList()) })
            }
        }
    }

    private val _refresh = MutableLiveData<Boolean>()
    val refresh: LiveData<Boolean> = _refresh

    private fun loadMedicines(q: String) = Pager(
        config = PagingConfig(50, enablePlaceholders = false),
        pagingSourceFactory = { PagingMedicineSource(q, loadMedicineUseCase) }
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

    fun search(orEmpty: String) {
        _searchQuery.postValue(orEmpty)
    }

}
