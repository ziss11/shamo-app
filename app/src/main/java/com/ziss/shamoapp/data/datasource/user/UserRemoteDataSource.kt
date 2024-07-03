package com.ziss.shamoapp.data.datasource.user

import com.ziss.shamoapp.data.models.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    suspend fun getCurrentUser(token: String): Flow<UserModel>
    suspend fun updateProfile(token: String, userModel: UserModel): Flow<UserModel>
}