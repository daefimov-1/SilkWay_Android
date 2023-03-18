package com.example.silkway.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.silkway.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        binding.tvForgotPassword.setOnClickListener {
            ForgotPasswordActivity.start(this)
        }
        binding.btnLogIn.setOnClickListener {
            //TODO Do auth check with rememberBox
            MainActivity.start(this)
        }
        binding.tvDontHaveAnAccount.setOnClickListener {
            SignUpActivity.start(this)
        }
        binding.tvSignUp.setOnClickListener {
            SignUpActivity.start(this)
        }
    }

    companion object {
        fun start(caller: AppCompatActivity?) {
            val intent: Intent = Intent(caller, LoginActivity::class.java)
            caller?.startActivity(intent)
        }
    }
}