package com.ziss.shamoapp.domain.usecases.auth

import com.ziss.shamoapp.common.ResultState
import com.ziss.shamoapp.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow

class UserGoogleSignIn(private val repository: AuthRepository) {
    fun execute(tokenId: String): Flow<ResultState<String>> {
        return repository.signInWithGoogle(tokenId)
    }
}