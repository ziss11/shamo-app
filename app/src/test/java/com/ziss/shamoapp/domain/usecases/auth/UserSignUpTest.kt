package com.ziss.shamoapp.domain.usecases.auth

import com.ziss.shamoapp.common.ResultState
import com.ziss.shamoapp.domain.repositories.AuthRepository
import com.ziss.shamoapp.domain.tRegisterUser
import com.ziss.shamoapp.domain.tUser
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
class UserSignUpTest {
    @Mock
    private lateinit var authRepository: AuthRepository
    private lateinit var usecase: UserSignUp

    @Before
    fun setUp() {
        authRepository = mock()
        usecase = UserSignUp(authRepository)
    }

    @Test
    fun `should add new user when execute function is called`() = runTest {
        // arrange
        `when`(authRepository.signUp(tRegisterUser)).thenReturn(
            MutableStateFlow(ResultState.Success(tUser))
        )
        // act
        val result = usecase.execute(tRegisterUser)
        // assert
        verify(authRepository).signUp(tRegisterUser)
        assertTrue(result.first() is ResultState.Success)
        assertEquals((result.first() as ResultState.Success).data, tUser)
    }
}