package com.ziss.shamoapp.domain.usecases.auth

import com.ziss.shamoapp.domain.repositories.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
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
class UserSignInTest {
    @Mock
    private lateinit var mockUserRepository: UserRepository
    private lateinit var usecase: UserSignIn

    @Before
    fun setUp() {
        mockUserRepository = mock()
        usecase = UserSignIn(mockUserRepository)
    }

    private val tEmail = "email@email.com"
    private val tPassword = "password"
    private val tAccessToken = "access_token"

    @Test
    fun `should return access token when user sign in`() = runTest {
        // arrange
        `when`(
            mockUserRepository.signIn(
                tEmail,
                tPassword
            )
        ).thenReturn(MutableStateFlow(tAccessToken))
        // act
        val result = usecase.execute(tEmail, tPassword)
        // assert
        assertEquals(result.first(), tAccessToken)
    }
}