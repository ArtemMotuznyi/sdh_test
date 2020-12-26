package com.developerartemmotuznyi.sdhtest.presentation.medicine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.developerartemmotuznyi.sdhtest.databinding.ItemMedicineBinding
import com.developerartemmotuznyi.sdhtest.domain.model.Medicine

class MedicinesAdapter : PagingDataAdapter<Medicine, MedicineViewHolder>(differ) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MedicineViewHolder(
        ItemMedicineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
}

class MedicineViewHolder(
    private val binding: ItemMedicineBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(Medicine: Medicine) {
        binding.title.text = Medicine.tradeLabel.name
        binding.subTitle.text = Medicine.manufacturer.name
    }

}