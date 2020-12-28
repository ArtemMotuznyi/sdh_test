package com.developerartemmotuznyi.sdhtest.presentation.container

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine
import com.developerartemmotuznyi.sdhtest.domain.usecase.FavoriteMedicineUseCase
import com.developerartemmotuznyi.sdhtest.domain.usecase.LoadMedicineUseCase
import com.developerartemmotuznyi.sdhtest.domain.usecase.UpdateMedicineStateUseCase
import com.developerartemmotuznyi.sdhtest.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.collect

class MedicinesViewModel @ViewModelInject constructor(
    private val loadMedicineUseCase: LoadMedicineUseCase,
    private val loadFavoriteMedicineUseCase: FavoriteMedicineUseCase,
    private val updateMedicineStateUseCase: UpdateMedicineStateUseCase
) : BaseViewModel() {

    private val _searchQuery = MutableLiveData("")
    val medicinesAll = _searchQuery.switchMap(::loadMedicines)

    val medicinesFavorite = _searchQuery.switchMap {
        liveData {
            loadFavoriteMedicineUseCase(it).collect { result ->
                launch({ result }, { emit(it) })
            }
        }
    }

    private fun loadMedicines(q: String) = Pager(
        config = PagingConfig(50, enablePlaceholders = false),
        pagingSourceFactory = { PagingMedicineSource(q, loadMedicineUseCase) }
    ).flow.cachedIn(viewModelScope).asLiveData()

    fun updateMedicineState(medicine: Medicine) {
        launch({ updateMedicineStateUseCase(medicine) })
    }

    fun search(orEmpty: String) {
        _searchQuery.postValue(orEmpty)
    }

}
