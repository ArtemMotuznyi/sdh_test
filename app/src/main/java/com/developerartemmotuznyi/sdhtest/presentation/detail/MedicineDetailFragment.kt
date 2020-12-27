package com.developerartemmotuznyi.sdhtest.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.developerartemmotuznyi.sdhtest.R
import com.developerartemmotuznyi.sdhtest.databinding.FragmentMedicineDetailBinding
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine
import com.developerartemmotuznyi.sdhtest.presentation.base.ViewModelFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MedicineDetailFragment : ViewModelFragment<FragmentMedicineDetailBinding, MedicineDetailViewModel>() {

	override val viewModel: MedicineDetailViewModel by viewModels()
	override val binding: FragmentMedicineDetailBinding by viewBinding(CreateMethod.INFLATE)
	private val args: MedicineDetailFragmentArgs by navArgs()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel.loadMedicine(args.medicineId)
	}

	override fun initSubscription() {
		super.initSubscription()
		viewModel.medicine.observe(viewLifecycleOwner) {
			it?.let(::showDetail)
		}
	}

	private fun showDetail(medicine: Medicine) {
		binding.name.text = medicine.tradeLabel.name
		binding.manufacturer.text = getString(R.string.detail_medicine_manufactorer_with_count, medicine.manufacturer.name, medicine.manufacturer.country.name)
		binding.composition.text = medicine.composition.description
		binding.compositionInn.text = medicine.composition.inn.name
		binding.phorm.text = medicine.composition.pharmForm.name
		binding.packaging.text = medicine.packaging.description
	}
}