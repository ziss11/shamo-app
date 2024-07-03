package com.ziss.shamoapp.domain.entities

import com.ziss.shamoapp.domain.tUser
import org.junit.Assert.assertEquals
import org.junit.Test

class UserTest {
    @Test
    fun `should have a correct attribute`() {
        // act
        val user = tUser
        // assert
        assertEquals(user.id, 1)
        assertEquals(user.name, "name")
        assertEquals(user.email, "email")
        assertEquals(user.phone, "phone")
        assertEquals(user.username, "username")
    }
}