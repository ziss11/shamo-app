package com.ziss.shamoapp.domain.usecases.auth

import com.ziss.shamoapp.common.ResultState
import com.ziss.shamoapp.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow

class UserSignOut(private val repository: AuthRepository) {
    fun execute(): Flow<ResultState<String>> {
        return repository.signOut()
    }
}