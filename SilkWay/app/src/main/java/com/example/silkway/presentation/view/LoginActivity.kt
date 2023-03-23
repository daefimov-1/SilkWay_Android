package com.example.silkway.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.silkway.data.model.User
import com.example.silkway.data.storage.LoginStorage
import com.example.silkway.databinding.ActivityLoginBinding
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginStorage by inject<LoginStorage>()

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
            loginStorage.saveUserInfo(
                User(
                    id = 0,
                    name = "Daniil Efimov",
                    email = "daefimov@edu.hse.ru",
                    isByer = true
                )
            )
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
        fun start(caller: FragmentActivity?) {
            val intent: Intent = Intent(caller, LoginActivity::class.java)
            caller?.startActivity(intent)
        }
    }
}