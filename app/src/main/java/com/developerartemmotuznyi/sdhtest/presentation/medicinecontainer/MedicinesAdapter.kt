package com.developerartemmotuznyi.sdhtest.presentation.medicinecontainer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.developerartemmotuznyi.sdhtest.databinding.ItemMedicineBinding
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine

class MedicinesAdapter(
        private val onItemClick: (Long) -> Unit,
        private val onFavoriteClick: (Medicine) -> Unit,
) : PagingDataAdapter<Medicine, MedicineViewHolder>(differ) {

    companion object {
        val differ = object : DiffUtil.ItemCallback<Medicine>() {
            override fun areItemsTheSame(oldItem: Medicine, newItem: Medicine): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Medicine, newItem: Medicine): Boolean =
                    oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MedicineViewHolder(
            ItemMedicineBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClick,
            onFavoriteClick
    )
}

class MedicineViewHolder(
        private val binding: ItemMedicineBinding,
        private val onItemClick: (Long) -> Unit,
        private val onFavoriteClick: (Medicine) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(medicine: Medicine) {
        binding.root.setOnClickListener { onItemClick(medicine.id) }
        binding.favorite.setOnClickListener { onFavoriteClick(medicine) }

        binding.title.text = medicine.tradeLabel.name
        binding.subTitle.text = medicine.manufacturer.name

        binding.favorite.isSelected = medicine.isSaved
    }

}