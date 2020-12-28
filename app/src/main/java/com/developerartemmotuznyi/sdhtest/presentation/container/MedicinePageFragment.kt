package com.developerartemmotuznyi.sdhtest.presentation.container

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.developerartemmotuznyi.networkconnectivitymanager.NetworkConnectivityProvider
import com.developerartemmotuznyi.sdhtest.databinding.FragmentMedicinesBinding
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine
import com.developerartemmotuznyi.sdhtest.presentation.base.ViewModelFragment
import com.developerartemmotuznyi.sdhtest.presentation.extensions.noInternetConnection
import javax.inject.Inject

abstract class MedicinePageFragment :
	ViewModelFragment<FragmentMedicinesBinding, MedicinesViewModel>(),
	NetworkConnectivityProvider.StateListener {

	@Inject
	lateinit var networkConnectivityProvider: NetworkConnectivityProvider

	override val viewModel: MedicinesViewModel by viewModels({ parentFragment ?: this })
	override val binding: FragmentMedicinesBinding by viewBinding(CreateMethod.INFLATE)
	protected val adapter = MedicinesAdapter(::navigateToDetail, ::updateMedicineState)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		progress = binding.progress

		binding.medicines.adapter = adapter
		binding.medicines.layoutManager = LinearLayoutManager(requireContext())

		adapter.addLoadStateListener {
			viewModel.showProgress(it.source.refresh is LoadState.Loading)
		}

		networkConnectivityProvider.addStateListener(this)
	}

	override fun onStateChanged(state: NetworkConnectivityProvider.NetworkState) {
		if (state is NetworkConnectivityProvider.NetworkState.NotConnectedState) {
			noInternetConnection(requireContext())
		} else {
			adapter.refresh()
		}
	}

	private fun navigateToDetail(id: Long) {
		findNavController().navigate(MedicinesContainerFragmentDirections.navigateToDetail(id))
	}

	private fun updateMedicineState(medicine: Medicine) {
		viewModel.updateMedicineState(medicine)
	}

}