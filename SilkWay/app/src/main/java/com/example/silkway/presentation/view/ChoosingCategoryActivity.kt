package com.example.silkway.presentation.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.elveum.elementadapter.SimpleBindingAdapter
import com.elveum.elementadapter.adapter
import com.elveum.elementadapter.addBinding
import com.example.silkway.data.model.CategoryItem
import com.example.silkway.data.model.ListOfCategories
import com.example.silkway.databinding.ActivityFilterBinding
import com.example.silkway.databinding.FilterItemBinding

class ChoosingCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilterBinding
    private lateinit var adapterForList: SimpleBindingAdapter<CategoryItem>

    companion object {
        const val LIST_CATEGORIES : String = "ChoosingCategoryActivity.LIST_CATEGORIES"
        fun start(caller: Activity, item: ListOfCategories) {
            val intent = Intent(caller, ChoosingCategoryActivity::class.java)
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
        adapterForList = createCompositeAdapter()
        if (listCategories != null) {
            adapterForList.submitList(listCategories.list)
        }
        binding.rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvCategories.adapter = adapterForList
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
                            adapterForList.submitList(item.listSubCategories)
                        } else {
                            val output = Intent()
                            output.putExtra(CreatingDealActivity.CATEGORY_RESULT, item.name)
                            setResult(RESULT_OK, output)
                            finish()
                        }
                    }
                }
            }
        }
    }
}