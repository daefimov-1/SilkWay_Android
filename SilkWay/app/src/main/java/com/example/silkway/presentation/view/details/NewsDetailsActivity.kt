package com.example.silkway.presentation.view.details

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.elveum.elementadapter.SimpleBindingAdapter
import com.elveum.elementadapter.adapter
import com.elveum.elementadapter.addBinding
import com.example.silkway.data.model.DetailsListItem
import com.example.silkway.data.model.NewsItem
import com.example.silkway.databinding.ActivityNewsDetailsBinding
import com.example.silkway.databinding.RvImageBlockItemBinding
import com.example.silkway.databinding.RvTextBlockItemBinding
import com.example.silkway.databinding.RvTitleBlockItemBinding

class NewsDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsDetailsBinding

    companion object {
        private const val OPEN_NEWS : String = "NewsDetailsActivity.NEWS_ITEM"
        fun start(caller: Activity, item: NewsItem?) {
            val intent = Intent(caller, NewsDetailsActivity::class.java)
            if (item != null) {
                intent.putExtra(OPEN_NEWS, item)
            }
            caller.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val item: NewsItem = intent.getParcelableExtra(OPEN_NEWS)!!
        binding.ibClose.setOnClickListener {
            finish()
        }

        val adapter = createCompositeAdapter()
        val list: List<DetailsListItem> = item.toListItem()
        adapter.submitList(list)
        binding.rvDetailsRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvDetailsRecyclerview.adapter = adapter
    }

    private fun createCompositeAdapter(): SimpleBindingAdapter<DetailsListItem> {
        return adapter<DetailsListItem> { // <--- Base type
            addBinding<DetailsListItem.Image, RvImageBlockItemBinding> {
                areItemsSame = { oldImage, newImage -> oldImage.id == newImage.id }
                bind { imageSrc ->
                    ivImage.setImageResource(imageSrc.imageSrc)
                }
            }

            addBinding<DetailsListItem.Title, RvTitleBlockItemBinding> {
                areItemsSame = { oldParameters, newParameters -> oldParameters.id == newParameters.id }
                areContentsSame = { oldItem, newItem -> oldItem == newItem }
                bind { item ->
                    tvTitle.text = item.text
                }
            }

            addBinding<DetailsListItem.Description, RvTextBlockItemBinding> {
                areItemsSame = { oldDescription, newDescription -> oldDescription.id == newDescription.id }
                areContentsSame = { oldItem, newItem -> oldItem.text == newItem.text }
                bind { description ->
                    tvText.text = description.text
                }
            }
        }
    }

    private fun NewsItem.toListItem(): List<DetailsListItem> {
        val list = ArrayList<DetailsListItem>()
        this.image.let {
            list.add(
                DetailsListItem.Image(
                    id = 0,
                    imageSrc = it
                )
            )
        }
        list.add(
            DetailsListItem.Title(
                id = 0,
                text = this.title
            )
        )
        this.text?.let {
            list.add(
                DetailsListItem.Description(
                    id = 0,
                    text = it
                )
            )
        }
        return list
    }
}