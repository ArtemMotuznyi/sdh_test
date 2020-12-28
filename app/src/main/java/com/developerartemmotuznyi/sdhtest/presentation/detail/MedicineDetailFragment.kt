package com.developerartemmotuznyi.sdhtest.presentation.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.developerartemmotuznyi.networkconnectivitymanager.NetworkConnectivityProvider
import com.developerartemmotuznyi.sdhtest.R
import com.developerartemmotuznyi.sdhtest.databinding.FragmentMedicineDetailBinding
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine
import com.developerartemmotuznyi.sdhtest.presentation.base.ViewModelFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MedicineDetailFragment :
	ViewModelFragment<FragmentMedicineDetailBinding, MedicineDetailViewModel>(),
	NetworkConnectivityProvider.StateListener {

	@Inject
	lateinit var networkConnectivityProvider: NetworkConnectivityProvider

	override val viewModel: MedicineDetailViewModel by viewModels()
	override val binding: FragmentMedicineDetailBinding by viewBinding(CreateMethod.INFLATE)
	private val args: MedicineDetailFragmentArgs by navArgs()

	private var favoriteItem: MenuItem? = null

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setHasOptionsMenu(true)
		progress = binding.progress

		viewModel.loadMedicine(args.medicineId)

		networkConnectivityProvider.addStateListener(this)
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.detail_menu, menu)
	}

	override fun onPrepareOptionsMenu(menu: Menu) {
		super.onPrepareOptionsMenu(menu)
		favoriteItem = menu.findItem(R.id.favorite)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.favorite -> viewModel.updateMedicineState()
			android.R.id.home -> findNavController().navigateUp()
		}
		return true
	}

	override fun initSubscription() {
		super.initSubscription()
		viewModel.medicine.observe(viewLifecycleOwner) {
			it?.let(::showDetail)
		}
	}

	private fun showDetail(medicine: Medicine) {
		binding.name.text = medicine.tradeLabel.name
		binding.manufacturer.text = getString(
			R.string.detail_medicine_manufactorer_with_count,
			medicine.manufacturer.name,
			medicine.manufacturer.country.name
		)
		binding.composition.text = medicine.composition.description
		binding.compositionInn.text = medicine.composition.inn.name
		binding.phorm.text = medicine.composition.pharmForm.name
		binding.packaging.text = medicine.packaging.description

		updateFavorite(medicine.isSaved)
	}

	private fun updateFavorite(saved: Boolean) {
		favoriteItem?.isVisible = true

		val imageRes = if (saved) R.drawable.ic_favorite_on else R.drawable.ic_favorite_off
		favoriteItem?.setIcon(imageRes)
	}

	override fun onStateChanged(state: NetworkConnectivityProvider.NetworkState) {
		if (state is NetworkConnectivityProvider.NetworkState.NotConnectedState) {
			showNoInternetConnection()
		} else {
			viewModel.loadMedicine(args.medicineId)
		}
	}

	override fun showNoInternetConnection() {
		super.showNoInternetConnection()
		favoriteItem?.isVisible = false
	}
}