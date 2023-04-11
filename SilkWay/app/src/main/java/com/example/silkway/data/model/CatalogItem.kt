package com.example.silkway.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class CatalogItem(
    @PrimaryKey
    var id: Int = 0,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "currency")
    val currency: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "section")
    val section: String,
    @ColumnInfo(name = "currentAmountRequests")
    val currentAmountRequests: Long,
    @ColumnInfo(name = "minAmountRequests")
    val minAmountRequests: Long,
    @ColumnInfo(name = "image")
    val image: String? = null,
    @ColumnInfo(name = "favourite")
    val favourite: Boolean = false,
    @ColumnInfo(name = "description")
    val description: String? = null,
    @ColumnInfo(name = "youRequested")
    val youRequested: Int = 0,
) : Parcelable