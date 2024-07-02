package com.ziss.shamoapp.common

sealed class ResultState<out R> private constructor() {
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Failed(val message: String) : ResultState<Nothing>()
    data object Initial : ResultState<Nothing>()
    data object Loading : ResultState<Nothing>()
}