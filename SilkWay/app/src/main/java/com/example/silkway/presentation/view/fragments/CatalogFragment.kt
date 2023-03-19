package com.example.silkway.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.silkway.R
import com.example.silkway.data.model.NewsItem
import com.example.silkway.databinding.FragmentCatalogBinding
import com.example.silkway.presentation.view.adapters.NewsAdapter

class CatalogFragment : Fragment() {

    private var newsBanner: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_catalog, container, false)

        newsBanner = view?.findViewById(R.id.news_banner)
        Log.i("LOGGG", newsBanner.toString())
        newsBanner?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter1 = NewsAdapter(activity)
        val listNews = listOf<NewsItem>(
            NewsItem(1, "Скидки до 50% в Adidas!"),
            NewsItem(2, "Новости дня"),
            NewsItem(3, "Падение рынка"),
            NewsItem(4, "Оптовики ликуют и зарабатывают"),
        )
        adapter1.submitList(listNews)
        newsBanner?.adapter = adapter1

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = CatalogFragment()
    }
}