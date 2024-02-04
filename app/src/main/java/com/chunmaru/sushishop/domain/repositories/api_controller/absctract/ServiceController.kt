package com.chunmaru.sushishop.domain.repositories.api_controller.absctract

import com.chunmaru.sushishop.data.api.NetworkResponse
import com.chunmaru.sushishop.data.api.ServerNotResponse
import retrofit2.Response
import java.net.SocketTimeoutException

abstract class ServiceController {

    protected suspend fun <T> processApiResponse(block: suspend () -> Response<T>): NetworkResponse<T> {
        return try {
            val response = block()
            return if (response.isSuccessful) {
                response.body()?.let { data ->
                    NetworkResponse.Success(data)
                } ?: NetworkResponse.Error(message = "Response body is null")
            } else {
                NetworkResponse.Error(message = response.message())
            }
        } catch (e: SocketTimeoutException) {
            NetworkResponse.Error(
                e = ServerNotResponse(),
                message = null
            )
        }

    }

}