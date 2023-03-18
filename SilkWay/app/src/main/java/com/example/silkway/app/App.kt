package com.example.silkway.app

import android.app.Application
import android.util.Log
import com.example.silkway.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.i("LOGGG", "koin start")
        startKoin {
            androidContext(this@App)
            modules(listOf(dataModule))
        }
        Log.i("LOGGG", "koin end")
    }
}