package com.example.silkway.presentation.view.diffcallbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.silkway.data.model.CatalogItem

class CatalogItemDiffCallback : DiffUtil.ItemCallback<CatalogItem>() {
    override fun areItemsTheSame(oldItem: CatalogItem, newItem: CatalogItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CatalogItem, newItem: CatalogItem): Boolean {
        return oldItem == newItem
    }
}