package com.ziss.shamoapp.data.datasource.remote.responses

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field: SerializedName("access_token")
    val token: String
)
