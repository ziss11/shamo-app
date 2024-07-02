package com.ziss.shamoapp.domain.usecases.auth

import com.ziss.shamoapp.common.Constants
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
class UserSignOutTest {
    @Mock
    private lateinit var authRepository: AuthRepository
    private lateinit var usecase: UserSignOut

    @Before
    fun setUp() {
        authRepository = mock()
        usecase = UserSignOut(authRepository)
    }

    @Test
    fun `should sign out user when execute function is called`() = runTest {
        // arrange
        `when`(authRepository.signOut()).thenReturn(
            MutableStateFlow(
                ResultState.Success(
                    Constants.signOutSuccessMessage
                )
            )
        )
        // act
        val result = usecase.execute()
        // assert
        verify(authRepository).signOut()
        assertTrue(result.first() is ResultState.Success)
        assertEquals((result.first() as ResultState.Success).data, Constants.signOutSuccessMessage)
    }
}