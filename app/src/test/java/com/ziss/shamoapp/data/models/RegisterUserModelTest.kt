package com.ziss.shamoapp.data.models

import com.ziss.shamoapp.domain.tRegisterUser
import com.ziss.shamoapp.domain.tRegisterUserModel
import org.junit.Assert.assertEquals
import org.junit.Test

class RegisterUserModelTest {
    @Test
    fun `should return a valid register user model`() {
        // act
        val registerUserModel = tRegisterUserModel
        // assert
        assertEquals(registerUserModel.fullName, "fullName")
        assertEquals(registerUserModel.username, "username")
        assertEquals(registerUserModel.email, "email")
        assertEquals(registerUserModel.password, "password")
    }

    @Test
    fun `should be a subclass of RegisterUser`() {
        // assert
        val result = tRegisterUserModel.toEntity()
        assertEquals(result, tRegisterUser)
    }
}