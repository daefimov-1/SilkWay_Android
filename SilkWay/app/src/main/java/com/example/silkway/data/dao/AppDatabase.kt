package com.example.silkway.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.silkway.data.model.CatalogItem

@Database(entities = [CatalogItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catalogItemsDao() : CatalogItemDao

    companion object {
        const val DATABASE_NAME = "CatalogItems.db"
    }
}