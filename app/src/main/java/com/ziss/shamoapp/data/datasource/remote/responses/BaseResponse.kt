package com.ziss.shamoapp.data.datasource.remote.responses

import com.google.gson.annotations.SerializedName

open class BaseResponse<T>(
    @field: SerializedName("status")
    open val status: String,

    @field: SerializedName("message")
    open val message: String? = null,

    @field: SerializedName("data")
    open val data: T? = null
)
