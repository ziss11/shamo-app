package com.ziss.shamoapp.domain.repositories

import com.ziss.shamoapp.common.ResultState
import com.ziss.shamoapp.domain.entities.RegisterUser
import com.ziss.shamoapp.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signIn(email: String, password: String): Flow<ResultState<String>>
    fun signUp(registerUser: RegisterUser): Flow<ResultState<User>>
    fun signInWithGoogle(tokenId: String): Flow<ResultState<String>>
    fun signOut(): Flow<ResultState<String>>
}