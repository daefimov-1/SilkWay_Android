package com.example.silkway.presentation.utils

import android.util.Patterns

object Validation {
    private const val PASSWORD_MIN_LENGTH = 8
    private const val NAME_MIN_LENGTH = 3
    fun String.isValidEmail(): Boolean {
        return !Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    fun String.isValidPassword(): Boolean {
        return this.length >= PASSWORD_MIN_LENGTH
    }

    fun String.isValidName(): Boolean {
        return this.length >= NAME_MIN_LENGTH
    }
}