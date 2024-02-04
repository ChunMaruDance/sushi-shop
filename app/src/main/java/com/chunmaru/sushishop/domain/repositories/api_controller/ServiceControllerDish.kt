package com.chunmaru.sushishop.domain.repositories.api_controller

import com.chunmaru.sushishop.data.api.NetworkResponse
import com.chunmaru.sushishop.data.models.dishes.DishResponse

interface ServiceControllerDish {

    suspend fun getDishesByCategory(category: String): NetworkResponse<List<DishResponse>>

    suspend fun getSpecialDish(): NetworkResponse<DishResponse>

    suspend fun getRecommendedDishes(): NetworkResponse<List<DishResponse>>


}