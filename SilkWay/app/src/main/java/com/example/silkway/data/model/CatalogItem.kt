package com.example.silkway.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatalogItem(
    var id: Int = 0,
    val price: Int,
    val currency: String,
    val name: String,
    val section: String,
    val currentAmountRequests: Long,
    val minAmountRequests: Long,
    val image: String? = null,
    val favourite: Boolean = false,
    val description: String? = null,
    val youRequested: Int = 0,
) : Parcelable