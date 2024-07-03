package com.ziss.shamoapp.data.datasource.auth

interface AuthLocalDataSource {
    suspend fun getAccessToken(): String
}