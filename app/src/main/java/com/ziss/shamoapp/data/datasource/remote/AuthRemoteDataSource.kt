package com.ziss.shamoapp.data.datasource.remote

import com.ziss.shamoapp.common.Constants
import com.ziss.shamoapp.data.datasource.remote.network.ApiService
import com.ziss.shamoapp.data.datasource.remote.responses.LoginResponse
import com.ziss.shamoapp.data.models.RegisterUserModel
import com.ziss.shamoapp.data.models.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface AuthRemoteDataSource {
    fun signUp(registerUser: RegisterUserModel): Flow<UserModel>
    fun signIn(email: String, password: String): Flow<String>
    fun signInWithGoogle(tokenId: String): Flow<String>
    fun signOut(token: String): Flow<String>
}

class AuthRemoteDataSourceImpl(private val apiService: ApiService) : AuthRemoteDataSource {
    override fun signUp(registerUser: RegisterUserModel): Flow<UserModel> = flow {
        val response = apiService.signUp(
            registerUser.fullName,
            registerUser.email,
            registerUser.username,
            registerUser.password
        )

        if (response.status == Constants.SUCCESS_STATUS) {
            val result = response.data as UserModel
            emit(result)
        } else {
            val message = response.message as String
            throw Exception(message)
        }
    }.flowOn(Dispatchers.IO)

    override fun signIn(email: String, password: String): Flow<String> = flow {
        val response = apiService.signIn(email, password)

        if (response.status == Constants.SUCCESS_STATUS) {
            val result = response.data as LoginResponse
            emit(result.token)
        } else {
            val message = response.message as String
            throw Exception(message)
        }
    }.flowOn(Dispatchers.IO)

    override fun signInWithGoogle(tokenId: String): Flow<String> = flow {
        val response = apiService.signInWithGoogle(tokenId)

        if (response.status == Constants.SUCCESS_STATUS) {
            val result = response.data as LoginResponse
            emit(result.token)
        } else {
            val message = response.message as String
            throw Exception(message)
        }
    }.flowOn(Dispatchers.IO)

    override fun signOut(token: String): Flow<String> = flow {
        val response = apiService.signOut(token)

        val message = response.message as String
        emit(message)
    }.flowOn(Dispatchers.IO)
}