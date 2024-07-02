package com.ziss.shamoapp.domain.entities

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val username: String,
    val googleId: String,
    val googleToken: String,
    val googleRefreshToken: String
)
