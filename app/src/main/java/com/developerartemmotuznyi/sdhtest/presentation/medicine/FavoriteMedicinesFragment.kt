package com.developerartemmotuznyi.sdhtest.presentation.medicine

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMedicinesFragment : MedicinePageFragment() {

	companion object {
		fun newInstance() = FavoriteMedicinesFragment()
	}


	override fun initSubscription() {
		super.initSubscription()
		viewModel.medicinesFavorite.observe(viewLifecycleOwner) {
			adapter.submitData(lifecycle, it)
		}
	}
}