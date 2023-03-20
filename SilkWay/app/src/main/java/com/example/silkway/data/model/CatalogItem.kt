package com.example.silkway.data.model

data class CatalogItem(
    var id: Int = 0,
    val price: Int,
    val currency: String,
    val name: String,
    val section: String,
    val currentAmountRequests: Long,
    val minAmountRequests: Long,
    val image: String? = null,
)