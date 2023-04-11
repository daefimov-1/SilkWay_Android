package com.example.silkway.app

import android.app.Application
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Room
import com.example.silkway.data.dao.AppDatabase
import com.example.silkway.data.dao.CatalogItemDao
import com.example.silkway.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    private var database : AppDatabase? = null
    private var catalogDao : CatalogItemDao? = null

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@App)
            modules(listOf(dataModule))
        }

        database = Room.databaseBuilder(applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME).allowMainThreadQueries()
            .build()

        catalogDao = database!!.catalogItemsDao()
    }

    fun getCatalogItemsDao() : CatalogItemDao? {
        return catalogDao
    }
    companion object {
        lateinit var instance: App
            private set
    }
}