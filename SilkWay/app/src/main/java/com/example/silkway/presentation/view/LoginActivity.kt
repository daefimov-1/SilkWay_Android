package com.example.silkway.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.silkway.R
import com.example.silkway.data.model.CatalogItem
import com.example.silkway.data.model.User
import com.example.silkway.data.storage.LoginStorage
import com.example.silkway.databinding.ActivityLoginBinding
import com.example.silkway.presentation.viewmodel.MainViewModel
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
            val mainViewModel : MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
            mainViewModel.insertCatalogList(makeCatalogList())
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

    private fun createCatalogItem(id: Int, amountRequested: Int = 0): CatalogItem {
        return CatalogItem(
            id = id,
            price = 1499,
            currency = "₽",
            name = "Likato Professional",
            section = "Face spray",
            currentAmountRequests = 394,
            minAmountRequests = 1000,
            image = "NO IMAGE HERE",
            description = resources.getString(R.string.example_description),
            youRequested = amountRequested
        )
    }
    private fun makeCatalogList(): List<CatalogItem> {
        return listOf<CatalogItem>(
            createCatalogItem(0),
            createCatalogItem(1,35),
            createCatalogItem(2),
            createCatalogItem(3),
            createCatalogItem(4, 4),
            createCatalogItem(5,100)
        )
    }
}