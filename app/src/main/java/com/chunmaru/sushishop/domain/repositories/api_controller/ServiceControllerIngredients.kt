package com.chunmaru.sushishop.domain.repositories.api_controller

import com.chunmaru.sushishop.data.api.NetworkResponse
import com.chunmaru.sushishop.data.models.IngredientResponse

interface ServiceControllerIngredients {

    suspend fun getIngredients(): NetworkResponse<List<IngredientResponse>>

}