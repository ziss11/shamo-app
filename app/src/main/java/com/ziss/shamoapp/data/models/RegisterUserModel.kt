package com.ziss.shamoapp.data.models

import com.ziss.shamoapp.domain.entities.RegisterUser

data class RegisterUserModel(
    val fullName: String,
    val username: String,
    val email: String,
    val password: String,
) {
    constructor(registerUser: RegisterUser) : this(
        registerUser.fullName,
        registerUser.username,
        registerUser.email,
        registerUser.password
    )

    fun toEntity() = RegisterUser(
        fullName,
        username,
        email,
        password
    )
}
