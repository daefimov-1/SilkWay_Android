package com.example.silkway.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.silkway.app.App
import com.example.silkway.data.model.CatalogItem

class MainViewModel : ViewModel() {
    private val catalogListLiveData : LiveData<List<CatalogItem>>? = App.instance.getCatalogItemsDao()?.getCatalogList()
    private val requestedCatalogListLiveData : LiveData<List<CatalogItem>>? = App.instance.getCatalogItemsDao()?.getRequestedCatalogList()
    private val favouritesCatalogListLiveData : LiveData<List<CatalogItem>>? = App.instance.getCatalogItemsDao()?.getFavouritesCatalogList()
    private val mineCatalogListLiveData : LiveData<List<CatalogItem>>? = App.instance.getCatalogItemsDao()?.getMineCatalogList()

    fun getCatalogList() : LiveData<List<CatalogItem>>?{
        return catalogListLiveData
    }

    fun getRequestedCatalogList() : LiveData<List<CatalogItem>>?{
        return requestedCatalogListLiveData
    }

    fun getMineCatalogList() : LiveData<List<CatalogItem>>?{
        return mineCatalogListLiveData
    }

    fun getFavouritesCatalogList() : LiveData<List<CatalogItem>>?{
        return favouritesCatalogListLiveData
    }

    fun getFilteredCatalogList(category: String): LiveData<List<CatalogItem>>?{
        return App.instance.getCatalogItemsDao()?.filteredCatalogItemList(category)
    }

    fun getCatalogItemById(id: Int) : LiveData<CatalogItem>? {
        return App.instance.getCatalogItemsDao()?.getCatalogItemById(id)
    }

    fun insertCatalogList(list: List<CatalogItem>) {
        App.instance.getCatalogItemsDao()?.insertAllCatalogItems(list)
    }

    fun insertCatalogItem(item: CatalogItem) {
        App.instance.getCatalogItemsDao()?.insertCatalogItem(item)
    }

    fun deleteCatalogInfo() {
        App.instance.getCatalogItemsDao()?.deleteAll()
    }

    fun updateCatalogItemYouRequested(amount: Int, id: Int) {
        App.instance.getCatalogItemsDao()?.updateCatalogItemRequestedAmount(amount, id)
    }

    fun updateCatalogItemIsFavourite(value: Boolean, id: Int) {
        App.instance.getCatalogItemsDao()?.updateCatalogItemIsFavourite(value, id)
    }
}