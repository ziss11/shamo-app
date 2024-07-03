package com.ziss.shamoapp.data.datasource.remote.network

import com.ziss.shamoapp.data.datasource.remote.responses.BaseResponse
import com.ziss.shamoapp.data.datasource.remote.responses.LoginResponse
import com.ziss.shamoapp.data.models.UserModel
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun signUp(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): BaseResponse<UserModel>

    @POST("login")
    suspend fun signIn(
        @Field("email") email: String,
        @Field("password") password: String
    ): BaseResponse<LoginResponse>

    @POST("auth/google")
    suspend fun signInWithGoogle(@Field("token_id") tokenId: String): BaseResponse<LoginResponse>

    @DELETE("logout")
    suspend fun signOut(@Header("Authorization") token: String): BaseResponse<Nothing>
}