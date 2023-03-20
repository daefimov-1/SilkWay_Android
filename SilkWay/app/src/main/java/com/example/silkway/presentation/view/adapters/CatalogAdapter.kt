package com.example.silkway.presentation.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.silkway.R
import com.example.silkway.data.model.CatalogItem
import com.example.silkway.presentation.view.diffcallbacks.CatalogItemDiffCallback

class CatalogAdapter(context: Context?) : ListAdapter<CatalogItem, CatalogAdapter.CatalogViewHolder>(
    CatalogItemDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogAdapter.CatalogViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.catalog_item, parent, false)
        return CatalogViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatalogAdapter.CatalogViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CatalogViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val price: TextView = itemView.findViewById(R.id.price)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val currentAmountRequests: TextView = itemView.findViewById(R.id.num_requests)
        private val minAmountRequests: TextView = itemView.findViewById(R.id.num_min_requests)
        //TODO image

        fun bind(item: CatalogItem) {
            price.text = item.price.toString() + " " + item.currency
            title.text = item.name + "/" + item.section
            currentAmountRequests.text = item.currentAmountRequests.toString()
            minAmountRequests.text = item.minAmountRequests.toString()
        }
    }
}
