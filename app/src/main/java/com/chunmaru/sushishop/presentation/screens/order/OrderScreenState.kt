package com.chunmaru.sushishop.presentation.screens.order

import com.chunmaru.sushishop.data.models.dishes.DishWithCounter

sealed class OrderScreenState {

    object Initial : OrderScreenState()

    object Pending : OrderScreenState()

    data class ShowData(
        val dishesWithCounters: List<DishWithCounter>
    ) : OrderScreenState()


}