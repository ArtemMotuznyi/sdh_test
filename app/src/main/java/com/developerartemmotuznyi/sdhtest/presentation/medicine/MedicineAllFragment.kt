package com.developerartemmotuznyi.sdhtest.presentation.medicine

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MedicineAllFragment : MedicinePageFragment() {

    companion object {
        fun newInstance() = MedicineAllFragment()
    }

    override fun initSubscription() {
        super.initSubscription()
        viewModel.medicinesAll.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }
    }

}