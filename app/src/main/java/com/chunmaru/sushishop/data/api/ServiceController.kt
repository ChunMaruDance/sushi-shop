package com.chunmaru.sushishop.data.api


import com.chunmaru.sushishop.data.models.CategoryResponse
import com.chunmaru.sushishop.data.models.admin.AdminResponse
import com.chunmaru.sushishop.data.models.dishes.DishResponse
import com.chunmaru.sushishop.data.models.login.LoginReceive
import com.chunmaru.sushishop.data.models.login.LoginResponse
import retrofit2.Response

class ServiceController(
    private val serviceApi: ServiceApi
) {

    suspend fun getDishesByCategory(category: String): NetworkResponse<List<DishResponse>> {
        val response = serviceApi.getDishesByCategory(category)
        return processApiResponse(response)
    }

    suspend fun login(password: String, login: String): NetworkResponse<LoginResponse> {
        val response = serviceApi.login(LoginReceive(login = login, password = password))
        return processApiResponse(response)
    }

    suspend fun getAdminProfile(token: String): NetworkResponse<AdminResponse> {
        val response = serviceApi.getProfile(token)
        return processApiResponse(response)
    }

    suspend fun getSpecialDish(): NetworkResponse<DishResponse> {
        val response = serviceApi.getSpecialDish()
        return processApiResponse(response)
    }

    suspend fun getCategories(): NetworkResponse<List<CategoryResponse>> {
        val response = serviceApi.getCategories()
        return processApiResponse(response)
    }


    private fun <T> processApiResponse(response: Response<T>): NetworkResponse<T> {
        return if (response.isSuccessful) {
            response.body()?.let { data ->
                NetworkResponse.Success(data)
            } ?: NetworkResponse.Error(message = "Response body is null")
        } else {
            NetworkResponse.Error(message = response.message())
        }
    }




}