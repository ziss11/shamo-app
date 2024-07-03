package com.ziss.shamoapp.data.datasource.user

interface UserLocalDataSource {
    suspend fun getAccessToken(): String
}