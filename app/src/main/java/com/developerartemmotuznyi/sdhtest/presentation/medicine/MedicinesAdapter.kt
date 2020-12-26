package com.developerartemmotuznyi.sdhtest.presentation.medicine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.developerartemmotuznyi.sdhtest.databinding.ItemMedicineBinding
import com.developerartemmotuznyi.sdhtest.local.model.MedicineEntity

class MedicinesAdapter : PagingDataAdapter<MedicineEntity, MedicineViewHolder>(differ) {

    companion object {
        val differ = object : DiffUtil.ItemCallback<MedicineEntity>() {
            override fun areItemsTheSame(oldItem: MedicineEntity, newItem: MedicineEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MedicineEntity, newItem: MedicineEntity): Boolean =
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

    fun bind(medicineEntity: MedicineEntity) {
        binding.title.text = medicineEntity.tradeLabel.name
        binding.subTitle.text = medicineEntity.manufacturerEntity.name
    }

}