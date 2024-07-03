package com.ziss.shamoapp.domain.usecases.user

import com.ziss.shamoapp.common.ResultState
import com.ziss.shamoapp.domain.entities.User
import com.ziss.shamoapp.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow

class UpdateProfile(private var repository: UserRepository) {
    fun execute(user: User): Flow<ResultState<User>> {
        return repository.updateProfile(user)
    }
}