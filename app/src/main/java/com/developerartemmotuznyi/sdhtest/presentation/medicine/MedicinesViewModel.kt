package com.developerartemmotuznyi.sdhtest.presentation.medicine

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.developerartemmotuznyi.sdhtest.domain.usecase.LoadMedicineUseCase
import com.developerartemmotuznyi.sdhtest.domain.usecase.SearchMedicineUseCase

class MedicinesViewModel @ViewModelInject constructor(
    private val loadMedicineUseCase: LoadMedicineUseCase,
    private val searchMedicineUseCase: SearchMedicineUseCase,
) : ViewModel() {

    private val _searchQuery = MutableLiveData("")
    val medicines = _searchQuery.switchMap {
        loadMedicines(it)
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


}
