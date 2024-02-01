package com.chunmaru.sushishop.data.api


import com.chunmaru.sushishop.data.models.CategoryResponse
import com.chunmaru.sushishop.data.models.IngredientResponse
import com.chunmaru.sushishop.data.models.admin.AdminResponse
import com.chunmaru.sushishop.data.models.dishes.DishResponse
import com.chunmaru.sushishop.data.models.login.LoginReceive
import com.chunmaru.sushishop.data.models.login.LoginResponse
import retrofit2.Response
import java.net.SocketTimeoutException

class ServiceController(
    private val serviceApi: ServiceApi
) {

    suspend fun getDishesByCategory(category: String): NetworkResponse<List<DishResponse>> {
        return processApiResponse { serviceApi.getDishesByCategory(category) }
    }

    suspend fun login(password: String, login: String): NetworkResponse<LoginResponse> {
        return processApiResponse {
            serviceApi.login(
                LoginReceive(
                    login = login,
                    password = password
                )
            )
        }
    }

    suspend fun getAdminProfile(token: String): NetworkResponse<AdminResponse> {
        return processApiResponse { serviceApi.getProfile(token) }
    }

    suspend fun getSpecialDish(): NetworkResponse<DishResponse> {
        return processApiResponse { serviceApi.getSpecialDish() }
    }

    suspend fun getCategories(): NetworkResponse<List<CategoryResponse>> {
        return processApiResponse { serviceApi.getCategories() }
    }

    suspend fun getRecommendedDishes(): NetworkResponse<List<DishResponse>> {
        return processApiResponse { serviceApi.getRecommended() }
    }

    suspend fun getIngredients(): NetworkResponse<List<IngredientResponse>> {
        return processApiResponse { serviceApi.getAllIngredients() }
    }

    private suspend fun <T> processApiResponse(block: suspend () -> Response<T>): NetworkResponse<T> {
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