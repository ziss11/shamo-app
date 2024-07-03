package com.ziss.shamoapp.data.models

import com.google.gson.Gson
import com.ziss.shamoapp.domain.tUser
import com.ziss.shamoapp.domain.tUserJsonResponse
import com.ziss.shamoapp.domain.tUserModel
import org.junit.Assert.assertEquals
import org.junit.Test

class UserModelTest {
    @Test
    fun `should return a valid model from JSON`() {
        // act
        val json = tUserJsonResponse
        val result = Gson().fromJson(json, UserModel::class.java)
        // assert
        assertEquals(result, tUserModel)
    }

    @Test
    fun `should return a valid json containing proper data`() {
        // act
        val json = Gson().toJson(tUserModel)
        // assert
        assertEquals(json, tUserJsonResponse)
    }

    @Test
    fun `should return a valid model from entity`() {
        // act
        val entity = tUser
        // assert
        val result = UserModel(entity)
        assertEquals(tUserModel, result)
    }

    @Test
    fun `should be a subclass of User`() {
        // act
        val result = tUserModel.toEntity()
        // assert
        assertEquals(result, tUser)
    }
}