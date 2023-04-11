package com.example.silkway.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.silkway.app.App
import com.example.silkway.data.model.CatalogItem

class MainViewModel : ViewModel() {
    private val catalogListLiveData : LiveData<List<CatalogItem>>? = App.instance.getCatalogItemsDao()?.getCatalogList()
    private val requestedCatalogListLiveData : LiveData<List<CatalogItem>>? = App.instance.getCatalogItemsDao()?.getRequestedCatalogList()

    fun getCatalogList() : LiveData<List<CatalogItem>>?{
        return catalogListLiveData
    }

    fun getRequestedCatalogList() : LiveData<List<CatalogItem>>?{
        return requestedCatalogListLiveData
    }

    fun insertCatalogList(list: List<CatalogItem>) {
        App.instance.getCatalogItemsDao()?.insertAllCatalogItems(list)
    }

    fun deleteCatalogInfo() {
        App.instance.getCatalogItemsDao()?.deleteAll()
    }
}