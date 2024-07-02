package com.ziss.shamoapp.domain.usecases.auth

import com.ziss.shamoapp.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow

class UserSignIn(private val userRepository: UserRepository) {
    fun execute(email: String, password: String): Flow<String> {
        return userRepository.signIn(email, password)
    }
}