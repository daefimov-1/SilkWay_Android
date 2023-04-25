package com.example.silkway.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListOfCategories (
    val list: List<CategoryItem>
) : Parcelable