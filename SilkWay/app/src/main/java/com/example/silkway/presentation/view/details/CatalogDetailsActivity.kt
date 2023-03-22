package com.example.silkway.presentation.view.details

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.elveum.elementadapter.SimpleBindingAdapter
import com.elveum.elementadapter.adapter
import com.elveum.elementadapter.addBinding
import com.example.silkway.R
import com.example.silkway.data.model.CatalogItem
import com.example.silkway.data.model.DetailsListItem
import com.example.silkway.databinding.ActivityCatalogDetailsBinding
import com.example.silkway.databinding.RvImageBlockItemBinding
import com.example.silkway.databinding.RvParamsBlockItemBinding
import com.example.silkway.databinding.RvTextBlockItemBinding

class CatalogDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogDetailsBinding

    companion object {
        private const val OPEN_NEWS : String = "CatalogDetailsActivity.CATALOG_ITEM"
        fun start(caller: Activity, item: CatalogItem?) {
            val intent = Intent(caller, CatalogDetailsActivity::class.java)
            if (item != null) {
                intent.putExtra(OPEN_NEWS, item)
            }
            caller.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val item: CatalogItem = intent.getParcelableExtra(OPEN_NEWS)!!
        binding.ibClose.setOnClickListener {
            finish()
        }

        setSrcStar(item.favourite)

        binding.ibStar.setOnClickListener {
            //TODO logic for star button
        }

        val adapter = adapter<DetailsListItem> { // <--- Base type
            addBinding<DetailsListItem.Image, RvImageBlockItemBinding> {
                areItemsSame = { oldImage, newImage -> oldImage.id == newImage.id }
                bind { imageSrc ->
                    //TODO set image
                }
            }

            addBinding<DetailsListItem.Parameters, RvParamsBlockItemBinding> {
                areItemsSame = { oldParameters, newParameters -> oldParameters.id == newParameters.id }
                areContentsSame = { oldItem, newItem -> oldItem == newItem }
                bind { params ->
                    tvParams.text = makeFullStringParameters(params)
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
        val list: List<DetailsListItem> = item.toListItem()
        adapter.submitList(list)
        binding.rvDetailsRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvDetailsRecyclerview.adapter = adapter
    }

    private fun setSrcStar(isFavourite: Boolean) {
        if (!isFavourite) {
            binding.ibStar.setImageResource(R.drawable.ic_star)
        } else {
            binding.ibStar.setImageResource(R.drawable.ic_star_favourite)
        }
    }

    private fun createCompositeAdapter(): SimpleBindingAdapter<DetailsListItem>? {
        //TODO decomposite onCreate
        return null
    }

    private fun CatalogItem.toListItem(): List<DetailsListItem> {
        val list = ArrayList<DetailsListItem>()
        this.image?.let {
            list.add(
                DetailsListItem.Image(
                    id = 0,
                    imageSrc = it
                )
            )
        }
        list.add(
            DetailsListItem.Parameters(
                id = 0,
                price = this.price,
                currency = this.currency,
                name = this.name,
                section = this.section,
                currentAmountRequests = this.currentAmountRequests,
                minAmountRequests = this.minAmountRequests
            )
        )
        this.description?.let {
            list.add(
                DetailsListItem.Description(
                    id = 0,
                    text = it
                )
            )
        }
        return list
    }

    private fun makeFullStringParameters(item: DetailsListItem.Parameters): String {
        var str = ""
        str += getString(R.string.param_name, item.name) + "\n"
        str += getString(R.string.param_category, item.section) + "\n"
        str += getString(R.string.param_price, item.price, item.currency) + "\n"
        str += getString(R.string.param_current_amount_requests, item.currentAmountRequests) + "\n"
        str += getString(R.string.param_min_amount_requests, item.minAmountRequests)
        return str
    }
}