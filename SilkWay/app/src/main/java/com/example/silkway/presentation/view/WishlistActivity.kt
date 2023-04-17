package com.example.silkway.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.silkway.databinding.ActivityWishlistBinding
import com.example.silkway.presentation.view.adapters.CatalogAdapter
import com.example.silkway.presentation.view.details.CatalogDetailsActivity
import com.example.silkway.presentation.viewmodel.MainViewModel

class WishlistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWishlistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        binding.rvFavourites.layoutManager = GridLayoutManager(this, 2)
        val adapter = CatalogAdapter(
            this,
            itemClickListener = { item -> CatalogDetailsActivity.start(this, item)}
        )
        val mainViewModel : MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.getFavouritesCatalogList()?.observe(this, Observer{
            adapter.submitList(it)
            if (it.isEmpty()) {
                byerHasFavourites(false)
            } else {
                byerHasFavourites(true)
            }
        })
        binding.rvFavourites.adapter = adapter
    }

    private fun byerHasFavourites(value: Boolean) {
        binding.vNothingWishlist.isVisible = !value
        binding.ivNothingWishlistImage.isVisible = !value
        binding.tvNothingWishlist.isVisible = !value
        binding.vGradient.isVisible = value
        binding.tvTextWishlist.isVisible = value
        binding.nsvScrollview.isVisible = value
    }

    companion object {
        fun start(caller: FragmentActivity?) {
            val intent: Intent = Intent(caller, WishlistActivity::class.java)
            caller?.startActivity(intent)
        }
    }
}