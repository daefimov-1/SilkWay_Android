package com.example.silkway.data.storage.impl

import android.content.Context
import com.example.silkway.data.model.User
import com.example.silkway.data.model.LoginResponse
import com.example.silkway.data.storage.LoginStorage

class LoginSharedPrefImpl(
    private val mCtx: Context
) : LoginStorage {

   override fun saveUserInfo(response: LoginResponse) {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(ID, response.id)
        editor.putString(NAME, response.name)
        editor.putString(EMAIL, response.email)
        editor.putString(TOKEN, response.token)
        editor.putBoolean(IS_SUPPLIER, response.isSupplier)
        editor.putBoolean(IS_BYER, response.isByer)
        editor.apply()
    }

    override fun isLoggedIn() : Boolean {
        val sharedPreferences =
            mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(ID, -1) != -1
    }

    override fun isSupplier(): Boolean {
        val sharedPreferences =
            mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(IS_SUPPLIER, false)
    }

    override fun isByer(): Boolean {
        val sharedPreferences =
            mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(IS_BYER, false)
    }

    override fun saveUserInfo(user: User) {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(ID, user.id)
        editor.putString(NAME, user.name)
        editor.putString(EMAIL, user.email)
        editor.putBoolean(IS_SUPPLIER, user.isSupplier)
        editor.putBoolean(IS_BYER, user.isByer)
        editor.apply()
    }

    override fun getUserInfo() : User {
        val sharedPreferences =
            mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return User(
            sharedPreferences.getInt(ID, -1),
            sharedPreferences.getString(NAME, "")?: "",
            sharedPreferences.getString(EMAIL, "1")?: "",
            sharedPreferences.getBoolean(IS_SUPPLIER, false),
            sharedPreferences.getBoolean(IS_BYER, false)
        )
    }

    override fun getUserToken() : String? {
        val sharedPreferences =
            mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(TOKEN, "")
    }

    override fun clearAll() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear().apply()
        editor.apply()
    }

    companion object {
        private const val SHARED_PREF_NAME = "shared_preference_user"
        private const val ID = "id"
        private const val NAME = "name"
        private const val EMAIL = "email"
        private const val TOKEN = "token"
        private const val IS_SUPPLIER = "isSupplier"
        private const val IS_BYER = "isByer"
    }
}
