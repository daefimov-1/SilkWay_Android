package com.example.silkway.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.silkway.R
import com.example.silkway.data.model.CatalogItem
import com.example.silkway.data.model.NewsItem
import com.example.silkway.presentation.view.adapters.CatalogAdapter
import com.example.silkway.presentation.view.adapters.NewsAdapter
import com.example.silkway.presentation.view.details.CatalogDetailsActivity


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
        val adapter1 = NewsAdapter(activity)
        val listNews = listOf<NewsItem>(
            NewsItem(1, "Скидки до 50% в Adidas!"),
            NewsItem(2, "Новости дня"),
            NewsItem(3, "Падение рынка"),
            NewsItem(4, "Оптовики ликуют и зарабатывают"),
        )
        adapter1.submitList(listNews)
        newsBanner?.adapter = adapter1

        //Catalog List
        catalog = view?.findViewById(R.id.catalogItems)
        catalog?.layoutManager = GridLayoutManager(activity, 2)
        val adapter2 = CatalogAdapter(
            activity,
            itemClickListener = { item -> CatalogDetailsActivity.start(requireActivity(), item)}
        )
        val listCatalogItems = makeCatalogList()
        adapter2.submitList(listCatalogItems)
        catalog?.adapter = adapter2

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = CatalogFragment()
    }

    private fun createCatalogItem(): CatalogItem {
        return CatalogItem(
            id = 1,
            price = 1499,
            currency = "₽",
            name = "Likato Professional",
            section = "Face spray",
            currentAmountRequests = 394,
            minAmountRequests = 1000,
            image = "NO IMAGE HERE",
            description = resources.getString(R.string.example_description)
        )
    }
    private fun makeCatalogList(): List<CatalogItem> {
        return listOf<CatalogItem>(
            createCatalogItem(),
            createCatalogItem(),
            createCatalogItem(),
            createCatalogItem(),
            createCatalogItem(),
            createCatalogItem()
        )
    }
}