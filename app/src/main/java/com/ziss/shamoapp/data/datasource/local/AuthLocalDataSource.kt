package com.ziss.shamoapp.data.datasource.local

interface AuthLocalDataSource {
    suspend fun getAccessToken(): String
}