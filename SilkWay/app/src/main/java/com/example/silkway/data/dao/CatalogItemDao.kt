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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCatalogItem(item: CatalogItem)

    @Insert
    fun insertAllCatalogItems(list: List<CatalogItem>)

    @Update
    fun updateCatalogItem(item: CatalogItem)

    @Delete
    fun deleteCatalogItem(item: CatalogItem)

    @Query("DELETE FROM catalogItem")
    fun deleteAll()
}