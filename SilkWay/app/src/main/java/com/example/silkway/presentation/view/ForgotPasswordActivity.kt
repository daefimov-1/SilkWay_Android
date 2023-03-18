package com.example.silkway.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.silkway.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        binding.btnGetLetter.setOnClickListener {
            //TODO Logic of forgot password
            finish()
        }
    }

    companion object {
        fun start(caller: AppCompatActivity?) {
            val intent: Intent = Intent(caller, ForgotPasswordActivity::class.java)
            caller?.startActivity(intent)
        }
    }
}