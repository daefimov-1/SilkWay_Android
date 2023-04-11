package com.example.silkway.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.silkway.R
import com.example.silkway.data.model.NewsItem
import com.example.silkway.presentation.view.adapters.CatalogAdapter
import com.example.silkway.presentation.view.adapters.NewsAdapter
import com.example.silkway.presentation.view.details.CatalogDetailsActivity
import com.example.silkway.presentation.view.details.NewsDetailsActivity
import com.example.silkway.presentation.viewmodel.MainViewModel


class CatalogFragment : Fragment() {

    private var newsBanner: RecyclerView? = null
    private var catalog: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_catalog, container, false)

        //News List
        newsBanner = view?.findViewById(R.id.news_banner)
        newsBanner?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter1 = NewsAdapter(
            activity,
            itemClickListener = { item -> NewsDetailsActivity.start(requireActivity(), item)}
        )
        val listNews = makeNewsList()
        adapter1.submitList(listNews)
        newsBanner?.adapter = adapter1

        //Catalog List
        catalog = view?.findViewById(R.id.catalogItems)
        catalog?.layoutManager = GridLayoutManager(activity, 2)
        val adapter2 = CatalogAdapter(
            activity,
            itemClickListener = { item -> CatalogDetailsActivity.start(requireActivity(), item)}
        )
        val mainViewModel : MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.getCatalogList()?.observe(requireActivity(), Observer{
            adapter2.submitList(it)
        })
        catalog?.adapter = adapter2

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = CatalogFragment()
    }
    private fun createNewsItem(title: String): NewsItem {
        return NewsItem(
            id = 1,
            title = title,
            text = resources.getString(R.string.example_description),
            image = "NO IMAGE HERE",
        )
    }
    private fun makeNewsList(): List<NewsItem> {
        return listOf<NewsItem>(
            createNewsItem("Скидки до 50% в Adidas!"),
            createNewsItem("Новости дня"),
            createNewsItem("Падение рынка"),
            createNewsItem("Оптовики ликуют и зарабатывают"),
        )
    }
}