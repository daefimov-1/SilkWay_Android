package com.example.silkway.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.silkway.R
import com.example.silkway.data.model.CategoryItem
import com.example.silkway.data.model.ListOfCategories
import com.example.silkway.data.model.NewsItem
import com.example.silkway.presentation.view.FilterActivity
import com.example.silkway.presentation.view.adapters.CatalogAdapter
import com.example.silkway.presentation.view.adapters.NewsAdapter
import com.example.silkway.presentation.view.details.CatalogDetailsActivity
import com.example.silkway.presentation.view.details.NewsDetailsActivity
import com.example.silkway.presentation.viewmodel.MainViewModel


class CatalogFragment : Fragment() {

    private var newsBanner: RecyclerView? = null
    private var catalog: RecyclerView? = null
    private var filterButton: ImageButton? = null
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

        val listOfCategories = ListOfCategories(
            listOf(
                CategoryItem(
                    0,
                "Electronics",
                    listOf(
                        CategoryItem(
                            0,
                            "Smartphones"
                        )
                    )
                ),
                CategoryItem(
                    1,
                    "Hobby"
                ),
                CategoryItem(
                    2,
                    "Sport"
                ),
                CategoryItem(
                    3,
                    "Rest"
                ),
                CategoryItem(
                    4,
                    "Animals"
                ),
                CategoryItem(
                    5,
                    "Equipment"
                ),
                CategoryItem(
                    6,
                    "Beauty"
                ),
                CategoryItem(
                    7,
                    "Toys"
                ),
                CategoryItem(
                    8,
                    "Furniture"
                ),
                CategoryItem(
                    9,
                    "For home",
                    listOf(
                        CategoryItem(
                            0,
                            "Cleaning"
                        )
                    )
                ),
                CategoryItem(
                    10,
                    "Automotive products"
                ),
                CategoryItem(
                    11,
                    "For repairs"
                ),
                CategoryItem(
                    12,
                    "Health"
                ),
                CategoryItem(
                    13,
                    "Stationery"
                ),
                CategoryItem(
                    14,
                    "Pet supplies"
                )
            )
        )
        filterButton = view?.findViewById(R.id.btn_filter)
        filterButton?.setOnClickListener {
            FilterActivity.start(requireActivity(), listOfCategories)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = CatalogFragment()
    }
    private fun createNewsItem(title: String, imageDrawable: Int, text: String): NewsItem {
        return NewsItem(
            id = 1,
            title = title,
            text = text,
            image = imageDrawable,
        )
    }
    private fun makeNewsList(): List<NewsItem> {
        return listOf<NewsItem>(
            createNewsItem("Discounts up to 60% in Adidas!", R.drawable.news_example_image2, "Especially for all fans of the Adidas brand, the company announces a new promotion for the entire range! Discounts of up to 60% on your favorite models of sneakers, sportswear and accessories are already waiting for you in all stores and on the official website of the brand!\n" +
                    "\n" +
                    "Now you can buy the most popular models of such lines as Superstar, Ultra boost, NMD, and many others at an incredibly favorable price!\n" +
                    "\n" +
                    "Also, as part of this promotion, you can order the delivery of goods for free, and the couriers of the company will deliver your purchase directly to your home.\n" +
                    "\n" +
                    "Don't miss the chance to update your wardrobe and buy stylish and comfortable clothes from your favorite brand Adidas at a great price! The promotion will last with a limited number of products, so hurry up to make your choice."),
            createNewsItem("Discounts on cosmetics", R.drawable.news_image2, ""),
            createNewsItem("May sale", R.drawable.news_image1, ""),
            createNewsItem("Shoe Sale!", R.drawable.news_example_image, ""),
        )
    }
}