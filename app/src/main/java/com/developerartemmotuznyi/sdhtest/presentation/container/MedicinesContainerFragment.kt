package com.developerartemmotuznyi.sdhtest.presentation.container

import android.app.SearchManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.developerartemmotuznyi.sdhtest.R
import com.developerartemmotuznyi.sdhtest.databinding.FragmentContainerMedicinesBinding
import com.developerartemmotuznyi.sdhtest.presentation.base.ViewModelFragment
import com.developerartemmotuznyi.sdhtest.presentation.favorite.FavoriteMedicinesFragment
import com.developerartemmotuznyi.sdhtest.presentation.feed.FeedMedicinesFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MedicinesContainerFragment :
		ViewModelFragment<FragmentContainerMedicinesBinding, MedicinesViewModel>(),
		SearchView.OnQueryTextListener {

	override val viewModel: MedicinesViewModel by viewModels()
	override val binding: FragmentContainerMedicinesBinding by viewBinding(CreateMethod.INFLATE)

	private val suggestionAdapter: SimpleCursorAdapter by lazy {
		SimpleCursorAdapter(
				requireContext(),
				android.R.layout.simple_list_item_1,
				null,
				arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1),
				intArrayOf(android.R.id.text1),
				0
		)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		(requireActivity() as? AppCompatActivity)?.setupActionBarWithNavController(findNavController())
		setHasOptionsMenu(true)

		val fragments = generateFragments()
		val adapter = MedicinesPageAdapter(this, fragments)
		binding.pager.adapter = adapter
		TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
			tab.text = adapter.getTitleRes(position)
		}.attach()
	}

	private fun generateFragments(): List<Pair<String, Fragment>> {
		return listOf(
				getString(R.string.all_medicines_tab_title) to FeedMedicinesFragment.newInstance(),
				getString(R.string.favorite_medicines_tab_title) to FavoriteMedicinesFragment.newInstance()
		)
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.medicine_menu, menu)
		val searchView = menu.findItem(R.id.action_search).actionView as? SearchView
		searchView?.setOnQueryTextListener(this)
		searchView?.suggestionsAdapter = suggestionAdapter
	}

	override fun onQueryTextSubmit(query: String?): Boolean = true

	override fun onQueryTextChange(newText: String?): Boolean {
		viewModel.search(newText.orEmpty())
		return true
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