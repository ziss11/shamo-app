package com.ziss.shamoapp.data.models

import com.google.gson.annotations.SerializedName
import com.ziss.shamoapp.domain.entities.User

data class UserModel(
    @SerializedName("id") val id: Int,
    @SerializedName("fullName") val fullName: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("username") val username: String,
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
