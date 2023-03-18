package com.example.silkway.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.silkway.R
import com.example.silkway.databinding.ActivityLoginBinding
import com.example.silkway.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        binding.btnRegister.setOnClickListener {
            //TODO
            finish()
        }
    }

    companion object {
        fun start(caller: AppCompatActivity?) {
            val intent: Intent = Intent(caller, SignUpActivity::class.java)
            caller?.startActivity(intent)
        }
    }
}