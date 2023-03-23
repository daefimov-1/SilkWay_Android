package com.example.silkway.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.silkway.data.model.User
import com.example.silkway.data.storage.LoginStorage
import com.example.silkway.databinding.ActivitySignUpBinding
import com.example.silkway.presentation.utils.Validation.isValidEmail
import com.example.silkway.presentation.utils.Validation.isValidName
import com.example.silkway.presentation.utils.Validation.isValidPassword
import org.koin.android.ext.android.inject

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val loginStorage by inject<LoginStorage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        binding.btnRegister.setOnClickListener {
            if (checkParamsAndSave()) {
                finish()
            }
        }
        binding.cbIsSupplier.setOnClickListener {
            binding.cbIsSme.isChecked = false
            binding.cbIsSupplier.isChecked = true
        }
        binding.cbIsSme.setOnClickListener {
            binding.cbIsSupplier.isChecked = false
            binding.cbIsSme.isChecked = true
        }
    }

    companion object {
        fun start(caller: AppCompatActivity?) {
            val intent = Intent(caller, SignUpActivity::class.java)
            caller?.startActivity(intent)
        }
    }

    private fun checkParamsAndSave(): Boolean {
        if (binding.textInputEdittextName.text!!.toString().isValidName() &&
            binding.textInputEdittextCompanyName.text!!.toString().isValidName() &&
            binding.textInputEdittextEmail.text!!.toString().isValidEmail() &&
            binding.textInputEdittextPassword.text!!.toString().isValidPassword() &&
            (binding.cbIsSupplier.isChecked || binding.cbIsSme.isChecked)
        ) {
            loginStorage.saveUserInfo(
                User(
                    id = 1,
                    name = binding.textInputEdittextName.text!!.toString(),
                    email = binding.textInputEdittextEmail.text!!.toString(),
                    isSupplier = binding.cbIsSupplier.isChecked,
                    isByer = binding.cbIsSme.isChecked
                )
            )
            return true
        }
        else {
            return false
        }
    }
}