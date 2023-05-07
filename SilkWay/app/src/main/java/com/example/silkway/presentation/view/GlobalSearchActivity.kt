package com.example.silkway.presentation.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.silkway.data.model.CatalogItem
import com.example.silkway.data.storage.LoginStorage
import com.example.silkway.databinding.ActivityGlobalSearchBinding
import com.example.silkway.presentation.view.adapters.CatalogAdapter
import com.example.silkway.presentation.view.details.CatalogDetailsActivity
import com.example.silkway.presentation.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class GlobalSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGlobalSearchBinding
    private val loginStorage by inject<LoginStorage>()

    companion object {
        fun start(caller: Activity) {
            val intent = Intent(caller, GlobalSearchActivity::class.java)
            caller.startActivity(intent)
        }
    }

    var list: List<CatalogItem> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGlobalSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        binding.searchItems.isVisible = false
        val mainViewModel : MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.getCatalogList()?.observe(this, Observer{
            list = it
        })

        binding.tvBack.setOnClickListener {
            finish()
        }

        binding.searchItems.layoutManager = GridLayoutManager(this, 2)
        val adapter1 = CatalogAdapter(
            this,
            itemClickListener = { item -> CatalogDetailsActivity.start(this, item)},
            loginStorage
        )

        binding.ibDone.setOnClickListener {
            val findingStr = binding.etGlobalSearch.text
            if (findingStr?.isNotEmpty() == true) {
                val filteredList = list.filter {
                    it.name.lowercase().contains(
                        findingStr.toString().lowercase()
                    )
                }
                if (filteredList.isNotEmpty()) {
                    binding.searchItems.isVisible = true
                    binding.ivWarning.isVisible = false
                    binding.tvWarningText.isVisible = false
                    adapter1.submitList(filteredList)
                    binding.searchItems.adapter = adapter1
                } else {
                    binding.searchItems.isVisible = false
                    binding.ivWarning.isVisible = true
                    binding.tvWarningText.text = "Nothing found :("
                    binding.tvWarningText.isVisible = true
                }
            }
        }
    }
}