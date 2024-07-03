package com.ziss.shamoapp.data.models

import com.ziss.shamoapp.domain.entities.RegisterUser

data class RegisterUserModel(
    val fullName: String,
    val username: String,
    val email: String,
    val password: String,
) {
    fun toEntity() = RegisterUser(
        fullName,
        username,
        email,
        password
    )
}
