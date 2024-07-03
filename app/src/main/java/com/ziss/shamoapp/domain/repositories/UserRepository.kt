package com.ziss.shamoapp.domain.repositories

import com.ziss.shamoapp.common.ResultState
import com.ziss.shamoapp.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getCurrentUser(): Flow<ResultState<User>>
    fun updateProfile(user: User): Flow<ResultState<User>>
}