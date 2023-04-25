package com.example.silkway.presentation.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.silkway.databinding.ActivityFilteredBinding
import com.example.silkway.presentation.view.adapters.CatalogAdapter
import com.example.silkway.presentation.view.details.CatalogDetailsActivity
import com.example.silkway.presentation.viewmodel.MainViewModel

class FilteredActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilteredBinding

    companion object {
        private const val CATEGORY : String = "FilteredActivity.Category"
        fun start(caller: Activity, category: String) {
            val intent = Intent(caller, FilteredActivity::class.java)
            intent.putExtra(CATEGORY, category)
            caller.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFilteredBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        val category = intent.getStringExtra(CATEGORY)

        binding.tvCategoryName.text = category

        binding.tvBack.setOnClickListener {
            finish()
        }

        //Catalog List
        binding.rvFilteredItems.layoutManager = GridLayoutManager(this, 2)
        val adapter2 = CatalogAdapter(
            this,
            itemClickListener = { item -> CatalogDetailsActivity.start(this, item)}
        )
        val mainViewModel : MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        if(category != null) {
            mainViewModel.getFilteredCatalogList(category)?.observe(this@FilteredActivity, Observer{
                adapter2.submitList(it)
            })
        }
        binding.rvFilteredItems.adapter = adapter2
    }
}