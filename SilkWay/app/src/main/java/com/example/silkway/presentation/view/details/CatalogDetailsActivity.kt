package com.example.silkway.presentation.view.details

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elveum.elementadapter.SimpleBindingAdapter
import com.elveum.elementadapter.adapter
import com.elveum.elementadapter.addBinding
import com.example.silkway.R
import com.example.silkway.data.model.CatalogItem
import com.example.silkway.data.model.DetailsListItem
import com.example.silkway.data.storage.LoginStorage
import com.example.silkway.databinding.ActivityCatalogDetailsBinding
import com.example.silkway.databinding.RvImageBlockItemBinding
import com.example.silkway.databinding.RvParamsBlockItemBinding
import com.example.silkway.databinding.RvTextBlockItemBinding
import com.example.silkway.presentation.view.fragments.dialogs.ByerBottomSheetDialog
import com.example.silkway.presentation.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class CatalogDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogDetailsBinding
    private val loginStorage by inject<LoginStorage>()
    private var isFavourite: Boolean = false

    companion object {
        private const val OPEN_ITEM : String = "CatalogDetailsActivity.CATALOG_ITEM"
        fun start(caller: Activity, item: CatalogItem?) {
            val intent = Intent(caller, CatalogDetailsActivity::class.java)
            if (item != null) {
                intent.putExtra(OPEN_ITEM, item)
            }
            caller.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val mainViewModel : MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val item: CatalogItem = intent.getParcelableExtra(OPEN_ITEM)!!
        binding.ibClose.setOnClickListener {
            finish()
        }
        isFavourite = item.favourite

        if(!loginStorage.isSupplier()) {
            binding.btnMakeRequest.isVisible = true
        }

        mainViewModel.getCatalogItemById(item.id)?.observe(this, Observer{
            setSrcStar(it.favourite)
        })

        binding.ibStar.setOnClickListener {
            mainViewModel.updateCatalogItemIsFavourite(!isFavourite, item.id)
        }

        binding.btnMakeRequest.setOnClickListener {
            ByerBottomSheetDialog(
                item_id = item.id,
                needed_amount = item.minAmountRequests - item.currentAmountRequests,
                youHaveRequested = item.youRequested
            ).show(supportFragmentManager, "tag")
        }

        val adapter = createCompositeAdapter()
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

    private fun createCompositeAdapter(): SimpleBindingAdapter<DetailsListItem> {
        return adapter<DetailsListItem> { // <--- Base type
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