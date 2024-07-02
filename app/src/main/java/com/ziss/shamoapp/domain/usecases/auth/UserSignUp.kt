package com.ziss.shamoapp.domain.usecases.auth

import com.ziss.shamoapp.common.ResultState
import com.ziss.shamoapp.domain.entities.RegisterUser
import com.ziss.shamoapp.domain.entities.User
import com.ziss.shamoapp.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow

class UserSignUp(private val repository: AuthRepository) {
    fun execute(registerUser: RegisterUser): Flow<ResultState<User>> {
        return repository.signUp(registerUser)
    }
}