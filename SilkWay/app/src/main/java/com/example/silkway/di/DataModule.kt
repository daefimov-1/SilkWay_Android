package com.example.silkway.di

import com.example.silkway.data.storage.LoginStorage
import com.example.silkway.data.storage.impl.LoginSharedPrefImpl
import org.koin.dsl.module

val dataModule = module {
    single<LoginStorage> {
        LoginSharedPrefImpl(mCtx = get())
    }
}