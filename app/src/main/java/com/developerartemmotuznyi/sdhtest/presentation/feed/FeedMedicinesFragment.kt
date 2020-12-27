package com.developerartemmotuznyi.sdhtest.presentation.feed

import com.developerartemmotuznyi.sdhtest.presentation.medicinecontainer.MedicinePageFragment
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
    }

}