package com.developerartemmotuznyi.sdhtest.presentation.feed

import com.developerartemmotuznyi.sdhtest.presentation.container.MedicinePageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedMedicinesFragment : MedicinePageFragment() {

    companion object {
        fun newInstance() = FeedMedicinesFragment()
    }

    override fun initSubscription() {
        super.initSubscription()
        viewModel.medicinesAll.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }

        viewModel.medicinesFavorite.observe(viewLifecycleOwner) {
            adapter.refresh()
        }
    }

}