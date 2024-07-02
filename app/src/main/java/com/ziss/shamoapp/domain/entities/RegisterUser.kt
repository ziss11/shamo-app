package com.ziss.shamoapp.domain.entities

data class RegisterUser(
    val fullName: String,
    val username: String,
    val email: String,
    val password: String
)
