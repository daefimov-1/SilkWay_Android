package com.example.silkway.data.model

data class NewsItem(
    var id: Int = 0,
    val title: String,
    val text: String? = null,
    val image: String? = null,
)