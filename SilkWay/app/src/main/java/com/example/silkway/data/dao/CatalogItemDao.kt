package com.example.silkway.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.silkway.data.model.CatalogItem

@Dao
interface CatalogItemDao {

    @Query("SELECT * FROM catalogItem")
    fun getCatalogList(): LiveData<List<CatalogItem>>

    @Query("SELECT * FROM catalogItem WHERE youRequested > 0")
    fun getRequestedCatalogList(): LiveData<List<CatalogItem>>

    @Query("SELECT * FROM catalogItem WHERE isMine")
    fun getMineCatalogList(): LiveData<List<CatalogItem>>

    @Query("SELECT * FROM catalogItem WHERE favourite")
    fun getFavouritesCatalogList(): LiveData<List<CatalogItem>>

    @Query("SELECT * FROM catalogItem WHERE id = :arg0")
    fun getCatalogItemById(arg0: Int): LiveData<CatalogItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCatalogItem(item: CatalogItem)

    @Insert
    fun insertAllCatalogItems(list: List<CatalogItem>)

    @Delete
    fun deleteCatalogItem(item: CatalogItem)

    @Query("DELETE FROM catalogItem")
    fun deleteAll()

    @Query("UPDATE catalogitem SET youRequested=:arg0 WHERE id = :arg1")
    fun updateCatalogItemRequestedAmount(arg0: Int, arg1: Int)

    @Query("UPDATE catalogitem SET favourite=:arg0 WHERE id = :arg1")
    fun updateCatalogItemIsFavourite(arg0: Boolean, arg1: Int)

    @Query("SELECT * FROM catalogItem WHERE section LIKE :arg0")
    fun filteredCatalogItemList(arg0: String): LiveData<List<CatalogItem>>
}