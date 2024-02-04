package com.chunmaru.sushishop.presentation.screens.order_details

import com.chunmaru.sushishop.data.models.dishes.DishWithCounter
import com.chunmaru.sushishop.data.storage.LocaleUserData

sealed class OrderDetailsScreenState {

    object Initial : OrderDetailsScreenState()

    object Pending : OrderDetailsScreenState()

    data class ShowData(
        val price: String,
        val usersData: LocaleUserData,
        val order: List<DishWithCounter>,
    ) : OrderDetailsScreenState()

}


sealed class OrderState {

    object Initial : OrderState()

    object Loading : OrderState()

    object NoValidData : OrderState()

    object ShortageGoods : OrderState()

    object SuccessOrder : OrderState()

}

