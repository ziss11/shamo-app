package com.ziss.shamoapp.data.datasource.auth

import com.ziss.shamoapp.data.models.RegisterUserModel
import com.ziss.shamoapp.data.models.UserModel
import kotlinx.coroutines.flow.Flow

interface AuthRemoteDataSource {
    fun signUp(registerUser: RegisterUserModel): Flow<UserModel>
    fun signIn(email: String, password: String): Flow<String>
    fun signInWithGoogle(tokenId: String): Flow<String>
    fun signOut(token: String): Flow<String>
}