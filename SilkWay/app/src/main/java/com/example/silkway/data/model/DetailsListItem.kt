package com.example.silkway.data.model

sealed class DetailsListItem {

    data class Image(
        val id: Int,
        val imageBase64String: String,
    ): DetailsListItem()

    data class Description(
        val id: Int,
        val text: String,
    ): DetailsListItem()

    data class Parameters(
        val id: Int,
        val price: Int,
        val currency: String,
        val name: String,
        val section: String,
        val currentAmountRequests: Int,
        val minAmountRequests: Int,
    ): DetailsListItem()

    data class Title(
        val id: Int,
        val text: String,
    ): DetailsListItem()

    data class Recommend(
        val id: Int,
        val items: List<CatalogItem>
    ): DetailsListItem()
}