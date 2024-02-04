package com.chunmaru.sushishop.data.api.controllers

import com.chunmaru.sushishop.data.api.NetworkResponse
import com.chunmaru.sushishop.data.api.ServiceApi
import com.chunmaru.sushishop.data.models.dishes.DishResponse
import com.chunmaru.sushishop.domain.repositories.api_controller.absctract.ServiceController
import com.chunmaru.sushishop.domain.repositories.api_controller.ServiceControllerDish

class ServiceControllerDishImpl(
    private val serviceApi: ServiceApi
) : ServiceController(),
    ServiceControllerDish {

    override suspend fun getDishesByCategory(category: String): NetworkResponse<List<DishResponse>> {
        return processApiResponse { serviceApi.getDishesByCategory(category) }
    }

    override suspend fun getSpecialDish(): NetworkResponse<DishResponse> {
        return processApiResponse { serviceApi.getSpecialDish() }
    }

    override suspend fun getRecommendedDishes(): NetworkResponse<List<DishResponse>> {
        return processApiResponse { serviceApi.getRecommended() }
    }


}