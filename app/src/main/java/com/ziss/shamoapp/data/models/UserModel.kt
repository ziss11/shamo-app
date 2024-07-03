package com.ziss.shamoapp.data.models

import com.google.gson.annotations.SerializedName
import com.ziss.shamoapp.domain.entities.User

data class UserModel(
    @field: SerializedName("id") val id: Int,
    @field: SerializedName("fullName") val fullName: String,
    @field: SerializedName("email") val email: String,
    @field: SerializedName("phone") val phone: String,
    @field: SerializedName("username") val username: String,
) {
    constructor(user: User) : this(
        user.id,
        user.fullName,
        user.email,
        user.phone,
        user.username
    )

    fun toEntity() = User(
        id,
        fullName,
        email,
        phone,
        username
    )
}
