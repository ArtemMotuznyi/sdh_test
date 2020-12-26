package com.developerartemmotuznyi.sdhtest.presentation.medicine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.developerartemmotuznyi.sdhtest.R
import com.developerartemmotuznyi.sdhtest.databinding.FragmentContainerMedicinesBinding
import com.developerartemmotuznyi.sdhtest.presentation.base.ViewModelFragment
import com.google.android.material.tabs.TabLayoutMediator

class MedicinesContainerFragment : ViewModelFragment<FragmentContainerMedicinesBinding, MedicinesViewModel>() {

	override val viewModel: MedicinesViewModel by viewModels()
	override val binding: FragmentContainerMedicinesBinding by viewBinding(CreateMethod.INFLATE)


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val fragments = generateFragments()
		val adapter = MedicinesPageAdapter(this, fragments)
		binding.pager.adapter = adapter
		TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
			tab.text = adapter.getTitleRes(position)
		}.attach()
	}

	private fun generateFragments(): List<Pair<String, Fragment>> {
		return listOf(
				getString(R.string.all_medicines_tab_title) to MedicineAllFragment.newInstance(),
				getString(R.string.favorite_medicines_tab_title) to FavoriteMedicinesFragment.newInstance()
		)
	}

	override fun initSubscription() {
		super.initSubscription()
	}
}

class MedicinesPageAdapter(
		fragment: Fragment,
		private val fragments: List<Pair<String, Fragment>>
) : FragmentStateAdapter(fragment) {

	override fun getItemCount(): Int = fragments.size

	override fun createFragment(position: Int): Fragment = fragments[position].second

	fun getTitleRes(position: Int): String = fragments[position].first

}