package com.example.silkway.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.core.view.isVisible
import com.example.silkway.R
import com.example.silkway.data.storage.LoginStorage
import com.example.silkway.presentation.view.fragments.AccountFragment
import com.example.silkway.presentation.view.fragments.SpecializedFragment
import com.example.silkway.presentation.view.fragments.CatalogFragment
import com.example.silkway.presentation.view.fragments.SupplierFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var accountButton : ImageButton
    private lateinit var catalogButton : ImageButton
    private lateinit var specificButton : ImageButton

    private lateinit var accountSelected : View
    private lateinit var catalogSelected : View
    private lateinit var specificSelected : View

    private val loginStorage by inject<LoginStorage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        accountButton = findViewById(R.id.ib_account)
        catalogButton = findViewById(R.id.ib_catalog)
        specificButton = findViewById(R.id.ib_specific)

        accountSelected = findViewById(R.id.v_account_selected)
        catalogSelected = findViewById(R.id.v_catalog_selected)
        specificSelected = findViewById(R.id.v_specific_selected)

        accountSelected.isVisible = false
        specificSelected.isVisible = false

        val accountFragment = AccountFragment()
        val catalogFragment = CatalogFragment()
        val specificFragment = SpecializedFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragments, catalogFragment)
            commit()
        }

        val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_animation)

        accountButton.setOnClickListener {
            makeSelectionInvisible()
            accountSelected.isVisible = true
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragments, accountFragment)
                commit()
            }
            accountButton.startAnimation(bounceAnimation)
        }
        catalogButton.setOnClickListener {
            makeSelectionInvisible()
            catalogSelected.isVisible = true
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragments, catalogFragment)
                commit()
            }
            catalogButton.startAnimation(bounceAnimation)
        }
        specificButton.setOnClickListener {
            makeSelectionInvisible()
            specificSelected.isVisible = true
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragments, specificFragment)
                commit()
            }
            specificButton.startAnimation(bounceAnimation)
        }

    }

    fun onNavigationItemSelected2(mainPage : MainPage) {
        when (mainPage) {
            MainPage.ACCOUNT -> {
                makeSelectionInvisible()
                accountSelected.isVisible = true
                supportFragmentManager.beginTransaction().apply {
                    add(R.id.fl_fragments, AccountFragment())
                    commit()
                }
            }
            MainPage.CATALOG -> {
                makeSelectionInvisible()
                catalogSelected.isVisible = true
                supportFragmentManager.beginTransaction().apply {
                    add(R.id.fl_fragments, CatalogFragment())
                    commit()
                }
            }
            MainPage.SPECIFIC -> {
                makeSelectionInvisible()
                specificSelected.isVisible = true
                supportFragmentManager.beginTransaction().apply {
                    add(R.id.fl_fragments, SpecializedFragment())
                    commit()
                }
            }
        }
    }

    fun makeSelectionInvisible() {
        accountSelected.isVisible = false
        specificSelected.isVisible = false
        catalogSelected.isVisible = false
    }

    enum class MainPage {
        ACCOUNT,
        CATALOG,
        SPECIFIC
    }

    companion object{
        fun start(caller : AppCompatActivity){
            val intent : Intent = Intent(caller, MainActivity::class.java)
            caller.startActivity(intent)
        }
    }
}