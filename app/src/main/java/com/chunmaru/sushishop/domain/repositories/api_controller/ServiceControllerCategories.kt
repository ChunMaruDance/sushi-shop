package com.chunmaru.sushishop.domain.repositories.api_controller

import com.chunmaru.sushishop.data.api.NetworkResponse
import com.chunmaru.sushishop.data.models.CategoryResponse

interface ServiceControllerCategories {

    suspend fun getCategories(): NetworkResponse<List<CategoryResponse>>

}