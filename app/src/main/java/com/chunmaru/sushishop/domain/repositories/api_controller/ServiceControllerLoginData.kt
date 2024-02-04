package com.chunmaru.sushishop.domain.repositories.api_controller

import com.chunmaru.sushishop.data.api.NetworkResponse
import com.chunmaru.sushishop.data.models.admin.AdminResponse
import com.chunmaru.sushishop.data.models.login.LoginResponse

interface ServiceControllerLoginData {

    suspend fun getAdminProfile(token: String): NetworkResponse<AdminResponse>

    suspend fun login(password: String, login: String): NetworkResponse<LoginResponse>

}