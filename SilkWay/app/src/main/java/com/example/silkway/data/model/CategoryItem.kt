package com.example.silkway.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryItem (
    val id: Int,
    val name: String,
    val listSubCategories: List<CategoryItem>? = null
) : Parcelable