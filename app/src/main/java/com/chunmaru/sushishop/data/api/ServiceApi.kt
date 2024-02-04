package com.chunmaru.sushishop.data.api

import com.chunmaru.sushishop.data.models.CategoryResponse
import com.chunmaru.sushishop.data.models.IngredientResponse
import com.chunmaru.sushishop.data.models.admin.AdminResponse
import com.chunmaru.sushishop.data.models.dishes.DishReceive
import com.chunmaru.sushishop.data.models.dishes.DishResponse
import com.chunmaru.sushishop.data.models.login.LoginReceive
import com.chunmaru.sushishop.data.models.login.LoginResponse
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ServiceApi {

    @POST("/dishes/add")
    suspend fun postDish(
        @Body body: DishReceive
    ): Response<String>

    @GET("/dishes/dish")
    suspend fun getDishById(
        @Query("id") id: Int
    ): Response<DishResponse>


    @GET("/dishes/by_category")
    suspend fun getDishesByCategory(
        @Query("category") category: String
    ): Response<List<DishResponse>>

    @POST("/login")
    suspend fun login(
        @Body body: LoginReceive
    ): Response<LoginResponse>

    @GET("/users/admin/info")
    suspend fun getProfile(
        @Query("Bearer-Authorization") token: String
    ): Response<AdminResponse>

    @GET("/dishes/special")
    suspend fun getSpecialDish(): Response<DishResponse>

    @GET("/dishes/categories/get")
    suspend fun getCategories(): Response<List<CategoryResponse>>

    @GET("/dishes/recommended")
    suspend fun getRecommended(): Response<List<DishResponse>>

    @GET("/dishes/ingredients")
    suspend fun getAllIngredients(): Response<List<IngredientResponse>>



}


object ApiClient {
    private const val BASE_URL = "http://192.168.1.4:8080"

    val serviceApi: ServiceApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .build()
            .create(ServiceApi::class.java)
    }
}

