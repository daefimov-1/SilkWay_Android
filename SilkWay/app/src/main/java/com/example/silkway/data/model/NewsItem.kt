package com.example.silkway.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsItem(
    var id: Int = 0,
    val title: String,
    val text: String? = null,
    val image: String,
) : Parcelable