package com.ziss.shamoapp.data.repositories

import com.ziss.shamoapp.common.ResultState
import com.ziss.shamoapp.data.datasource.local.AuthLocalDataSource
import com.ziss.shamoapp.data.datasource.remote.AuthRemoteDataSource
import com.ziss.shamoapp.data.models.RegisterUserModel
import com.ziss.shamoapp.domain.entities.RegisterUser
import com.ziss.shamoapp.domain.entities.User
import com.ziss.shamoapp.domain.repositories.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource,
    private val localDataSource: AuthLocalDataSource
) : AuthRepository {
    override fun signIn(email: String, password: String): Flow<ResultState<String>> = flow {
        emit(ResultState.Loading)

        try {
            val result = remoteDataSource.signIn(email, password).map {
                ResultState.Success(it)
            }
            emitAll(result)
        } catch (e: Exception) {
            emit(ResultState.Failed(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun signUp(registerUser: RegisterUser): Flow<ResultState<User>> = flow {
        emit(ResultState.Loading)

        try {
            val result = remoteDataSource.signUp(RegisterUserModel(registerUser)).map {
                ResultState.Success(it.toEntity())
            }
            emitAll(result)
        } catch (e: Exception) {
            emit(ResultState.Failed(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun signInWithGoogle(tokenId: String): Flow<ResultState<String>> = flow {
        emit(ResultState.Loading)

        try {
            val result = remoteDataSource.signInWithGoogle(tokenId).map {
                ResultState.Success(it)
            }
            emitAll(result)
        } catch (e: Exception) {
            emit(ResultState.Failed(e.message.toString()))
        }
    }

    override fun signOut(): Flow<ResultState<String>> = flow {
        emit(ResultState.Loading)

        try {
            val token = localDataSource.getAccessToken()
            val result = remoteDataSource.signOut(token).map {
                ResultState.Success(it)
            }
            emitAll(result)
        } catch (e: Exception) {
            emit(ResultState.Failed(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}