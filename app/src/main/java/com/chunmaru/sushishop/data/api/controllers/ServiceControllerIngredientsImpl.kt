package com.chunmaru.sushishop.data.api.controllers

import com.chunmaru.sushishop.data.api.NetworkResponse
import com.chunmaru.sushishop.data.api.ServiceApi
import com.chunmaru.sushishop.data.models.IngredientResponse
import com.chunmaru.sushishop.domain.repositories.api_controller.ServiceControllerIngredients
import com.chunmaru.sushishop.domain.repositories.api_controller.absctract.ServiceController


class ServiceControllerIngredientsImpl(
    private val serviceApi: ServiceApi
) : ServiceController(), ServiceControllerIngredients {


    override suspend fun getIngredients(): NetworkResponse<List<IngredientResponse>> {
        return processApiResponse { serviceApi.getAllIngredients() }
    }

}