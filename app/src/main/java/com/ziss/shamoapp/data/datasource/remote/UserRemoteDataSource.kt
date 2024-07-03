package com.ziss.shamoapp.data.datasource.remote

import com.ziss.shamoapp.data.models.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    fun getCurrentUser(token: String): Flow<UserModel>
    fun updateProfile(token: String, userModel: UserModel): Flow<UserModel>
}