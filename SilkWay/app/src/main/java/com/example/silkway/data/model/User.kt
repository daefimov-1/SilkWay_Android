package com.example.silkway.data.model

data class User(
    var id: Int = 0,
    val name: String,
    val email: String,
    val isSupplier: Boolean = false,
    val isByer: Boolean = false,
)