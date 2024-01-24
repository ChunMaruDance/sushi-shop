package com.chunmaru.sushishop.data.api


sealed class NetworkResponse<T> {

    data class Error<T>(
        val e: Exception? = null,
        val message: String?
    ) : NetworkResponse<T>()

    data class Success<T>(
        val data: T
    ) : NetworkResponse<T>()


}