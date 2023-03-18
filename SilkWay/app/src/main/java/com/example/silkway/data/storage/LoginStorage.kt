package com.example.silkway.data.storage

import com.example.silkway.data.model.LoginResponse
import com.example.silkway.data.model.User

interface LoginStorage {
    fun getUserToken() : String?
    fun getUserInfo() : User
    fun isLoggedIn() : Boolean
    fun isSupplier(): Boolean
    fun isByer(): Boolean
    fun saveUserInfo(response: LoginResponse)
    fun saveUserInfo(user: User)
    fun clearAll()
}