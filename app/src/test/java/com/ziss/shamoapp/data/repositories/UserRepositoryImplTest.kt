package com.ziss.shamoapp.data.repositories

import com.ziss.shamoapp.common.ResultState
import com.ziss.shamoapp.data.datasource.auth.AuthLocalDataSource
import com.ziss.shamoapp.data.datasource.user.UserRemoteDataSource
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
class UserRepositoryImplTest {
    @Mock
    private lateinit var remoteDataSource: UserRemoteDataSource

    @Mock
    private lateinit var localDataSource: AuthLocalDataSource

    private lateinit var userRepository: UserRepositoryImpl

    @Before
    fun setUp() {
        remoteDataSource = mock()
        localDataSource = mock()
        userRepository = UserRepositoryImpl(remoteDataSource, localDataSource)
    }

    private val tAccessToken = "access_token"

    @Test
    fun `Get Current User - should return a current user when the call to remote data source is successfully`() =
        runTest {
            // arrange
            `when`(localDataSource.getAccessToken()).thenReturn(tAccessToken)
            `when`(remoteDataSource.getCurrentUser(tAccessToken)).thenReturn(flow { emit(tUserModel) })
            // act
            val flow = userRepository.getCurrentUser()
            val results = flow.toList()
            // assert
            assertEquals(results[0], ResultState.Loading)
            assertTrue(results[1] is ResultState.Success)
            assertEquals(results[1], ResultState.Success(tUser))
        }

    @Test
    fun `Get Current User - should return error message when the call to remote data source is unsuccessful`() =
        runTest {
            // arrange
            `when`(localDataSource.getAccessToken()).thenReturn(tAccessToken)
            `when`(remoteDataSource.getCurrentUser(tAccessToken)).thenReturn(flow {
                throw Exception("Server error")
            })
            // act
            val flow = userRepository.getCurrentUser()
            val results = flow.toList()
            // assert
            assertEquals(results[0], ResultState.Loading)
            assertTrue(results[1] is ResultState.Failed)
            assertEquals(results[1], ResultState.Failed("Server error"))
        }

    @Test
    fun `Update User - should return a updated user when the call to remote data source is successfully`() =
        runTest {
            // arrange
            `when`(localDataSource.getAccessToken()).thenReturn(tAccessToken)
            `when`(remoteDataSource.updateProfile(tAccessToken, tUserModel)).thenReturn(flow {
                emit(tUserModel)
            })
            // act
            val flow = userRepository.updateProfile(tUser)
            val results = flow.toList()
            // assert
            assertEquals(results[0], ResultState.Loading)
            assertTrue(results[1] is ResultState.Success)
            assertEquals(results[1], ResultState.Success(tUser))
        }

    @Test
    fun `Update User - should return error message when the call to remote data source is unsuccessful`() =
        runTest {
            // arrange
            `when`(localDataSource.getAccessToken()).thenReturn(tAccessToken)
            `when`(remoteDataSource.updateProfile(tAccessToken, tUserModel)).thenReturn(flow {
                throw Exception("Server Error")
            })
            // act
            val flow = userRepository.updateProfile(tUser)
            val results = flow.toList()
            // assert
            assertEquals(results[0], ResultState.Loading)
            assertTrue(results[1] is ResultState.Failed)
            assertEquals(results[1], ResultState.Failed("Server Error"))
        }
}