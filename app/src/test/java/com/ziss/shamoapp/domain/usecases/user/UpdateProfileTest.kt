package com.ziss.shamoapp.domain.usecases.user

import com.ziss.shamoapp.common.ResultState
import com.ziss.shamoapp.domain.repositories.UserRepository
import com.ziss.shamoapp.domain.tUser
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
class UpdateProfileTest {
    @Mock
    private lateinit var userRepository: UserRepository
    private lateinit var usecase: UpdateProfile

    @Before
    fun setUp() {
        userRepository = mock()
        usecase = UpdateProfile(userRepository)
    }

    @Test
    fun `should update user when execute function is called`() = runTest {
        // arrange
        `when`(userRepository.updateProfile(tUser)).thenReturn(
            MutableStateFlow(
                ResultState.Success(
                    tUser
                )
            )
        )
        // act
        val result = usecase.execute(tUser)
        // assert
        verify(userRepository).updateProfile(tUser)
        assertEquals(result.first(), ResultState.Success(tUser))
    }
}