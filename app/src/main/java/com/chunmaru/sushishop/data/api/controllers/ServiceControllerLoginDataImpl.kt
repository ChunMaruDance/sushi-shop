package com.chunmaru.sushishop.data.api.controllers

import com.chunmaru.sushishop.data.api.NetworkResponse
import com.chunmaru.sushishop.data.api.ServiceApi
import com.chunmaru.sushishop.data.models.admin.AdminResponse
import com.chunmaru.sushishop.data.models.login.LoginReceive
import com.chunmaru.sushishop.data.models.login.LoginResponse
import com.chunmaru.sushishop.domain.repositories.api_controller.ServiceControllerLoginData
import com.chunmaru.sushishop.domain.repositories.api_controller.absctract.ServiceController

class ServiceControllerLoginDataImpl(
    private val serviceApi: ServiceApi
) : ServiceController(), ServiceControllerLoginData {

    override suspend fun getAdminProfile(token: String): NetworkResponse<AdminResponse> {
        return processApiResponse { serviceApi.getProfile(token) }
    }

    override suspend fun login(password: String, login: String): NetworkResponse<LoginResponse> {
        return processApiResponse {
            serviceApi.login(
                LoginReceive(
                    login = login,
                    password = password
                )
            )
        }
    }

}