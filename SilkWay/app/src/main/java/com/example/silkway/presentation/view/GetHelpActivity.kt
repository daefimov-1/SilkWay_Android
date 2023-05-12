package com.example.silkway.presentation.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.silkway.databinding.ActivityGetHelpBinding

class GetHelpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetHelpBinding

    companion object {
        fun start(caller: Activity) {
            val intent = Intent(caller, GetHelpActivity::class.java)
            caller.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGetHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        binding.tvBack.setOnClickListener {
            finish()
        }

        binding.btnSend.setOnClickListener {
            finish()
        }
    }
}