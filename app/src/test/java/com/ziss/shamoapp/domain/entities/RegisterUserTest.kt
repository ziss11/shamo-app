package com.ziss.shamoapp.domain.entities

import com.ziss.shamoapp.domain.tRegisterUser
import org.junit.Assert.assertEquals
import org.junit.Test

class RegisterUserTest {
    @Test
    fun `should have a correct attribute`() {
        // act
        val registerUser = tRegisterUser
        // assert
        assertEquals(registerUser.fullName, "fullName")
        assertEquals(registerUser.username, "username")
        assertEquals(registerUser.email, "email")
        assertEquals(registerUser.password, "password")
    }
}