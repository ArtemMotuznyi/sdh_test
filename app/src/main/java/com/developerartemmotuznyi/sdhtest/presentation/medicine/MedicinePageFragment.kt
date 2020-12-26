package com.developerartemmotuznyi.sdhtest.presentation.medicine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.developerartemmotuznyi.sdhtest.databinding.FragmentMedicinesBinding
import com.developerartemmotuznyi.sdhtest.presentation.base.ViewModelFragment

abstract class MedicinePageFragment : ViewModelFragment<FragmentMedicinesBinding, MedicinesViewModel>() {

	override val viewModel: MedicinesViewModel by viewModels({ parentFragment ?: this })

	override val binding: FragmentMedicinesBinding by viewBinding(CreateMethod.INFLATE)

	protected val adapter = MedicinesAdapter()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.medicines.adapter = adapter
		binding.medicines.layoutManager = LinearLayoutManager(requireContext())
	}

}