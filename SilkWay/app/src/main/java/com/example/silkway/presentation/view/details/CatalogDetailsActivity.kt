package com.example.silkway.presentation.view.details

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.silkway.R
import com.example.silkway.data.model.CatalogItem
import com.example.silkway.databinding.ActivityCatalogDetailsBinding

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
    }

    private fun setSrcStar(isFavourite: Boolean) {
        if (!isFavourite) {
            binding.ibStar.setImageResource(R.drawable.ic_star)
        } else {
            binding.ibStar.setImageResource(R.drawable.ic_star_favourite)
        }
    }
}