package com.chunmaru.sushishop.presentation.screens.admin

import com.chunmaru.sushishop.data.models.admin.AdminResponse

sealed class AdminScreenState {

    object Initial : AdminScreenState()

    object Pending : AdminScreenState()

    class ShowData(
        val admin: AdminResponse
    ) : AdminScreenState()

}
