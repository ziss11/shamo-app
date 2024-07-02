package com.ziss.shamoapp.domain.repositories

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun signIn(email: String, password: String): Flow<String>
}