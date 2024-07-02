package com.ziss.shamoapp.domain.usecases.auth

import com.ziss.shamoapp.common.ResultState
import com.ziss.shamoapp.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserGoogleSignInTest {
    @Mock
    private lateinit var authRepository: AuthRepository
    private lateinit var usecase: UserGoogleSignIn

    @Before
    fun setUp() {
        authRepository = mock()
        usecase = UserGoogleSignIn(authRepository)
    }

    private val tGoogleTokenId = "google_token_id"
    private val tAccessToken = "access_token"

    @Test
    fun `should sign in user with google account when execute function is called`() = runTest {
        // arrange
        `when`(authRepository.signInWithGoogle(tGoogleTokenId)).thenReturn(
            MutableStateFlow(
                ResultState.Success(
                    tAccessToken
                )
            )
        )
        // act
        val result = usecase.execute(tGoogleTokenId)
        // assert
        verify(authRepository).signInWithGoogle(tGoogleTokenId)
        assertTrue(result.first() is ResultState.Success)
        assertEquals((result.first() as ResultState.Success).data, tAccessToken)
    }
}