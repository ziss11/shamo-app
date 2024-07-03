package com.ziss.shamoapp.domain.usecases.auth

import com.ziss.shamoapp.common.ResultState
import com.ziss.shamoapp.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserSignInTest {
    @Mock
    private lateinit var authRepository: AuthRepository
    private lateinit var usecase: UserSignIn

    @Before
    fun setUp() {
        authRepository = mock()
        usecase = UserSignIn(authRepository)
    }

    private val tEmail = "email@email.com"
    private val tPassword = "password"
    private val tAccessToken = "access_token"

    @Test
    fun `should sign in user when execute function is called`() = runTest {
        // arrange
        `when`(
            authRepository.signIn(
                tEmail,
                tPassword
            )
        ).thenReturn(MutableStateFlow(ResultState.Success(tAccessToken)))
        // act
        val result = usecase.execute(tEmail, tPassword)
        // assert
        verify(authRepository).signIn(tEmail, tPassword)
        assertEquals(result.first(), ResultState.Success(tAccessToken))
    }
}