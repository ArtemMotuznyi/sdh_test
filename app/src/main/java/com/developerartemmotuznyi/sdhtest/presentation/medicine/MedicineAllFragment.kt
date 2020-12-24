package com.developerartemmotuznyi.sdhtest.presentation.medicine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.developerartemmotuznyi.sdhtest.databinding.FragmentMedicinesAllBinding
import com.developerartemmotuznyi.sdhtest.presentation.base.ViewModelFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MedicineAllFragment : ViewModelFragment<FragmentMedicinesAllBinding, MedicinesViewModel>() {

    override val viewModel: MedicinesViewModel by viewModels()
    override val binding: FragmentMedicinesAllBinding by viewBinding(CreateMethod.INFLATE)

    private val adapter = MedicinesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.medicines.adapter = adapter
        binding.medicines.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun initSubscription() {
        super.initSubscription()
        viewModel.medicines.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }
    }
}