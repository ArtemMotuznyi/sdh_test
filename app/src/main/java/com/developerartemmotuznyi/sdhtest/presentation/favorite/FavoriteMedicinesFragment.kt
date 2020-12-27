package com.developerartemmotuznyi.sdhtest.presentation.favorite

import androidx.paging.PagingData
import com.developerartemmotuznyi.sdhtest.presentation.medicinecontainer.MedicinePageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMedicinesFragment : MedicinePageFragment() {

	companion object {
		fun newInstance() = FavoriteMedicinesFragment()
	}


	override fun initSubscription() {
		super.initSubscription()
		viewModel.medicinesFavorite.observe(viewLifecycleOwner) {
			adapter.submitData(lifecycle, PagingData.from(it))
		}
	}
}