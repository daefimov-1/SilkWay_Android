package com.example.silkway.presentation.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.silkway.R
import com.example.silkway.data.model.NewsItem
import com.example.silkway.presentation.view.diffcallbacks.NewsItemDiffCallback

class NewsAdapter(context: Context?) : ListAdapter<NewsItem, NewsAdapter.NewsViewHolder>(NewsItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.title)
        //TODO image

        fun bind(item: NewsItem) {
            title.text = item.title
        }
    }
}