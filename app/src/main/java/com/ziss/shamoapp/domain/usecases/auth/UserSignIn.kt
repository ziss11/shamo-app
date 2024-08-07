package com.ziss.shamoapp.domain.usecases.auth

import com.ziss.shamoapp.common.ResultState
import com.ziss.shamoapp.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow

class UserSignIn(private val repository: AuthRepository) {
    fun execute(email: String, password: String): Flow<ResultState<String>> {
        return repository.signIn(email, password)
    }
}