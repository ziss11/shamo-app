package com.ziss.shamoapp.data.datasource.auth

import com.ziss.shamoapp.common.Constants
import com.ziss.shamoapp.data.datasource.remote.AuthRemoteDataSource
import com.ziss.shamoapp.data.datasource.remote.AuthRemoteDataSourceImpl
import com.ziss.shamoapp.data.datasource.remote.network.ApiService
import com.ziss.shamoapp.data.datasource.remote.responses.BaseResponse
import com.ziss.shamoapp.data.datasource.remote.responses.LoginResponse
import com.ziss.shamoapp.domain.tRegisterUserModel
import com.ziss.shamoapp.domain.tUserModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AuthRemoteDataSourceTest {
    @Mock
    private lateinit var apiService: ApiService
    private lateinit var remoteDataSource: AuthRemoteDataSource

    @Before
    fun setUp() {
        apiService = mock()
        remoteDataSource = AuthRemoteDataSourceImpl(apiService)
    }

    private val tTokenId = "token_id"
    private val tEmail = "email"
    private val tPassword = "password"
    private val tAccessToken = "access_token"

    @Test
    fun `Sign Up - should return a registered user when the call to api service is successfully`() =
        runTest {
            // arrange
            `when`(
                apiService.signUp(
                    tRegisterUserModel.fullName,
                    tRegisterUserModel.email,
                    tRegisterUserModel.username,
                    tRegisterUserModel.password
                )
            ).thenReturn(
                BaseResponse(
                    status = Constants.SUCCESS_STATUS,
                    data = tUserModel
                )
            )
            // act
            val result = remoteDataSource.signUp(tRegisterUserModel)
            // assert
            assertEquals(result.first(), tUserModel)
        }

    @Test
    fun `Sign Up - should return error message when the call to api service is unsuccessful`() =
        runTest {
            // arrange
            `when`(
                apiService.signUp(
                    tRegisterUserModel.fullName,
                    tRegisterUserModel.email,
                    tRegisterUserModel.username,
                    tRegisterUserModel.password
                )
            ).thenReturn(
                BaseResponse(
                    status = Constants.FAILED_STATUS,
                    message = "Error"
                )
            )
            // act
            val result = remoteDataSource.signUp(tRegisterUserModel)
            // assert
            try {
                result.first()
            } catch (e: Exception) {
                assertEquals("Error", e.message)
            }
        }

    @Test
    fun `Sign In - should return access token when the call to api service is successfully`() =
        runTest {
            // arrange
            `when`(apiService.signIn(tEmail, tPassword)).thenReturn(
                BaseResponse(
                    status = Constants.SUCCESS_STATUS,
                    data = LoginResponse(token = tAccessToken)
                )
            )
            // act
            val result = remoteDataSource.signIn(tEmail, tPassword)
            // assert
            assertEquals(result.first(), tAccessToken)
        }

    @Test
    fun `Sign In - should return error message when the call to api service is unsuccessful`() =
        runTest {
            // arrange
            `when`(apiService.signIn(tEmail, tPassword)).thenReturn(
                BaseResponse(
                    status = Constants.FAILED_STATUS,
                    message = "Error"
                )
            )
            // act
            val result = remoteDataSource.signIn(tEmail, tPassword)
            // assert
            try {
                result.first()
            } catch (e: Exception) {
                assertEquals("Error", e.message)
            }
        }

    @Test
    fun `Sign In with Google - should return access token when the call to api service is successfully`() =
        runTest {
            // arrange
            `when`(apiService.signInWithGoogle(tTokenId)).thenReturn(
                BaseResponse(
                    status = Constants.SUCCESS_STATUS,
                    data = LoginResponse(token = tAccessToken)
                )
            )
            // act
            val result = remoteDataSource.signInWithGoogle(tTokenId)
            // assert
            assertEquals(result.first(), tAccessToken)
        }

    @Test
    fun `Sign In with Google - should return error message when the call to api service is unsuccessful`() =
        runTest {
            // arrange
            `when`(apiService.signInWithGoogle(tTokenId)).thenReturn(
                BaseResponse(
                    status = Constants.FAILED_STATUS,
                    message = "Error"
                )
            )
            // act
            val result = remoteDataSource.signInWithGoogle(tTokenId)
            // assert
            try {
                result.first()
            } catch (e: Exception) {
                assertEquals("Error", e.message)
            }
        }

    @Test
    fun `Sign Out - should return success message when the call to api service is successfully`() =
        runTest {
            // arrange
            `when`(apiService.signOut(tAccessToken)).thenReturn(
                BaseResponse(
                    status = Constants.SUCCESS_STATUS,
                    message = Constants.SIGN_OUT_SUCCESS_MESSAGE
                )
            )
            // act
            val result = remoteDataSource.signOut(tAccessToken)
            // assert
            assertEquals(result.first(), Constants.SIGN_OUT_SUCCESS_MESSAGE)
        }

    @Test
    fun `Sign Out - should return error message when the call to api service is unsuccessful`() =
        runTest {
            // arrange
            `when`(apiService.signOut(tAccessToken)).thenReturn(
                BaseResponse(
                    status = Constants.SUCCESS_STATUS,
                    message = "Error"
                )
            )
            // act
            val result = remoteDataSource.signOut(tAccessToken)
            // assert
            try {
                result.first()
            } catch (e: Exception) {
                assertEquals(e.message, "Error")
            }
        }
}