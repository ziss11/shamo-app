package com.ziss.shamoapp.data.repositories

import com.ziss.shamoapp.common.ResultState
import com.ziss.shamoapp.data.datasource.auth.AuthLocalDataSource
import com.ziss.shamoapp.data.datasource.user.UserRemoteDataSource
import com.ziss.shamoapp.data.models.UserModel
import com.ziss.shamoapp.domain.entities.User
import com.ziss.shamoapp.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: AuthLocalDataSource
) : UserRepository {
    override fun getCurrentUser(): Flow<ResultState<User>> = flow {
        emit(ResultState.Loading)

        try {
            val token = localDataSource.getAccessToken()
            val result = remoteDataSource.getCurrentUser(token).map {
                ResultState.Success(it.toEntity())
            }
            emitAll(result)
        } catch (e: Exception) {
            emit(ResultState.Failed(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun updateProfile(user: User): Flow<ResultState<User>> = flow {
        emit(ResultState.Loading)

        try {
            val token = localDataSource.getAccessToken()
            val result = remoteDataSource.updateProfile(token, UserModel(user)).map {
                ResultState.Success(it.toEntity())
            }
            emitAll(result)
        } catch (e: Exception) {
            emit(ResultState.Failed(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}