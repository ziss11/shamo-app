package com.ziss.shamoapp.data.repositories

import com.ziss.shamoapp.common.Constants
import com.ziss.shamoapp.common.ResultState
import com.ziss.shamoapp.data.datasource.local.AuthLocalDataSource
import com.ziss.shamoapp.data.datasource.remote.AuthRemoteDataSource
import com.ziss.shamoapp.domain.tRegisterUser
import com.ziss.shamoapp.domain.tRegisterUserModel
import com.ziss.shamoapp.domain.tUser
import com.ziss.shamoapp.domain.tUserModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AuthRepositoryImplTest {
    @Mock
    private lateinit var localDataSource: AuthLocalDataSource

    @Mock
    private lateinit var remoteDataSource: AuthRemoteDataSource

    private lateinit var authRepository: AuthRepositoryImpl

    @Before
    fun setUp() {
        localDataSource = mock()
        remoteDataSource = mock()
        authRepository = AuthRepositoryImpl(remoteDataSource, localDataSource)
    }

    private val tTokenId = "token_id"
    private val tEmail = "email"
    private val tPassword = "password"
    private val tAccessToken = "access_token"

    @Test
    fun `Sign Up - should return registered user when the call to remote data source is successfully`() =
        runTest {
            // arrange
            `when`(remoteDataSource.signUp(tRegisterUserModel)).thenReturn(flow {
                emit(tUserModel)
            })
            // act
            val flow = authRepository.signUp(tRegisterUser)
            val results = flow.toList()
            // assert
            assertEquals(results[0], ResultState.Loading)
            assertTrue(results[1] is ResultState.Success)
            assertEquals(results[1], ResultState.Success(tUser))
        }

    @Test
    fun `Sign Up - should return error message when the call to remote data source is unsuccessful`() =
        runTest {
            // arrange
            `when`(remoteDataSource.signUp(tRegisterUserModel)).thenReturn(flow {
                throw Exception("Server Error")
            })
            // act
            val flow = authRepository.signUp(tRegisterUser)
            val results = flow.toList()
            // assert
            assertEquals(results[0], ResultState.Loading)
            assertTrue(results[1] is ResultState.Failed)
            assertEquals(results[1], ResultState.Failed("Server Error"))
        }

    @Test
    fun `Sign In - should return access token when the call to remote data source is successfully`() =
        runTest {
            // arrange
            `when`(remoteDataSource.signIn(tEmail, tPassword)).thenReturn(flow {
                emit(tAccessToken)
            })
            // act
            val flow = authRepository.signIn(tEmail, tPassword)
            val results = flow.toList()
            // assert
            assertEquals(results[0], ResultState.Loading)
            assertTrue(results[1] is ResultState.Success)
            assertEquals(results[1], ResultState.Success(tAccessToken))
        }

    @Test
    fun `Sign In - should return error message when the call to remote data source is unsuccessful`() =
        runTest {
            // arrange
            `when`(remoteDataSource.signIn(tEmail, tPassword)).thenReturn(flow {
                throw Exception("Server Error")
            })
            // act
            val flow = authRepository.signIn(tEmail, tPassword)
            val results = flow.toList()
            // assert
            assertEquals(results[0], ResultState.Loading)
            assertTrue(results[1] is ResultState.Failed)
            assertEquals(results[1], ResultState.Failed("Server Error"))
        }

    @Test
    fun `Sign In with Google - should return access token when the call to remote data source is successfully`() =
        runTest {
            // arrange
            `when`(remoteDataSource.signInWithGoogle(tTokenId)).thenReturn(flow {
                emit(tAccessToken)
            })
            // act
            val flow = authRepository.signInWithGoogle(tTokenId)
            val results = flow.toList()
            // assert
            assertEquals(results[0], ResultState.Loading)
            assertTrue(results[1] is ResultState.Success)
            assertEquals(results[1], ResultState.Success(tAccessToken))
        }

    @Test
    fun `Sign In with Google - should return error message when the call to remote data source is unsuccessful`() =
        runTest {
            // arrange
            `when`(remoteDataSource.signInWithGoogle(tTokenId)).thenReturn(flow {
                throw Exception("Server Error")
            })
            // act
            val flow = authRepository.signInWithGoogle(tTokenId)
            val results = flow.toList()
            // assert
            assertEquals(results[0], ResultState.Loading)
            assertTrue(results[1] is ResultState.Failed)
            assertEquals(results[1], ResultState.Failed("Server Error"))
        }

    @Test
    fun `Sign Out - should return success message when the call to remote data source is successfully`() =
        runTest {
            // arrange
            `when`(localDataSource.getAccessToken()).thenReturn(tAccessToken)
            `when`(remoteDataSource.signOut(tAccessToken)).thenReturn(flow {
                emit(Constants.SIGN_OUT_SUCCESS_MESSAGE)
            })
            // act
            val flow = authRepository.signOut()
            val results = flow.toList()
            // assert
            assertEquals(results[0], ResultState.Loading)
            assertTrue(results[1] is ResultState.Success)
            assertEquals(results[1], ResultState.Success(Constants.SIGN_OUT_SUCCESS_MESSAGE))
        }

    @Test
    fun `Sign Out - should return error message when the call to remote data source is unsuccessful`() =
        runTest {
            // arrange
            `when`(localDataSource.getAccessToken()).thenReturn(tAccessToken)
            `when`(remoteDataSource.signOut(tAccessToken)).thenReturn(flow {
                throw Exception("Server Error")
            })
            // act
            val flow = authRepository.signOut()
            val results = flow.toList()
            // assert
            assertEquals(results[0], ResultState.Loading)
            assertTrue(results[1] is ResultState.Failed)
            assertEquals(results[1], ResultState.Failed("Server Error"))
        }
}