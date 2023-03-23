package com.example.silkway.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.silkway.R
import com.example.silkway.data.storage.LoginStorage
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private val loginStorage by inject<LoginStorage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        if (loginStorage.isLoggedIn()) {
            MainActivity.start(this)
            finish()
        } else {
            LoginActivity.start(this)
            finish()
        }
    }
}