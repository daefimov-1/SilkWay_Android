package com.example.silkway.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
            //TODO Go to main screen
        } else {
            Log.i("LOGGG", "login screen start")
            LoginActivity.start(this)
            finish()
        }
    }
}