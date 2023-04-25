package com.example.silkway.presentation.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.elveum.elementadapter.SimpleBindingAdapter
import com.elveum.elementadapter.adapter
import com.elveum.elementadapter.addBinding
import com.example.silkway.data.model.CategoryItem
import com.example.silkway.data.model.ListOfCategories
import com.example.silkway.databinding.ActivityFilterBinding
import com.example.silkway.databinding.FilterItemBinding

class FilterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilterBinding

    companion object {
        private const val LIST_CATEGORIES : String = "FilterActivity.LIST_CATEGORIES"
        fun start(caller: Activity, item: ListOfCategories) {
            val intent = Intent(caller, FilterActivity::class.java)
            intent.putExtra(LIST_CATEGORIES, item)
            caller.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        binding.tvBack.setOnClickListener {
            finish()
        }

        val listCategories = intent.getParcelableExtra(LIST_CATEGORIES) as ListOfCategories?
        val adapter = createCompositeAdapter()
        if (listCategories != null) {
            Log.i("LOGGG", "" + listCategories.list.size)
            adapter.submitList(listCategories.list)
        }
        binding.rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvCategories.adapter = adapter
    }

    private fun createCompositeAdapter(): SimpleBindingAdapter<CategoryItem> {
        return adapter<CategoryItem> {
            addBinding<CategoryItem, FilterItemBinding> {
                areItemsSame = { oldItem, newItem -> oldItem.id == newItem.id }
                areContentsSame = { oldItem, newItem -> oldItem.name == newItem.name }
                bind { item ->
                    this.tvCategoryText.text = item.name
                    this.tvCategoryText.setOnClickListener {
                        if (item.listSubCategories != null && item.listSubCategories.size > 0) {
                            FilterActivity.start(
                                this@FilterActivity,
                                ListOfCategories(
                                    list = item.listSubCategories
                                )
                            )
                        } else {
                            FilteredActivity.start(this@FilterActivity, item.name)
                        }
                    }
                }
            }
        }
    }
}