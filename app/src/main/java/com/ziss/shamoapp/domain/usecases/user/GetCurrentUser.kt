package com.ziss.shamoapp.domain.usecases.user

import com.ziss.shamoapp.common.ResultState
import com.ziss.shamoapp.domain.entities.User
import com.ziss.shamoapp.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentUser(private val repository: UserRepository) {
    fun execute(): Flow<ResultState<User>> {
        return repository.getCurrentUser()
    }
}