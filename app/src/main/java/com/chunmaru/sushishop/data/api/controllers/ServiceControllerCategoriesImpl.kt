package com.chunmaru.sushishop.data.api.controllers

import com.chunmaru.sushishop.data.api.NetworkResponse
import com.chunmaru.sushishop.data.api.ServiceApi
import com.chunmaru.sushishop.data.models.CategoryResponse
import com.chunmaru.sushishop.domain.repositories.api_controller.ServiceControllerCategories
import com.chunmaru.sushishop.domain.repositories.api_controller.absctract.ServiceController

class ServiceControllerCategoriesImpl(
    private val serviceApi: ServiceApi
) : ServiceController(), ServiceControllerCategories {

    override suspend fun getCategories(): NetworkResponse<List<CategoryResponse>> {
        return processApiResponse { serviceApi.getCategories() }
    }

}