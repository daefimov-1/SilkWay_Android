package com.example.silkway.data.model

data class LoginResponse(
    var id: Int = 0,
    val name: String,
    val email: String,
    val token: String,
    val isSupplier: Boolean = false,
    val isByer: Boolean = false,
)