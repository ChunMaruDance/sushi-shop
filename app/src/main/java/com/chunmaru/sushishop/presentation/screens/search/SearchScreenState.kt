package com.chunmaru.sushishop.presentation.screens.search

import com.chunmaru.sushishop.data.models.dishes.Dish


sealed class SearchScreenState {


    object Pending : SearchScreenState()

    object ServerNotResponse : SearchScreenState()

    data class ShowData(
        val recommended: RecommendedState,
        val searchDish: List<Dish>
    ) : SearchScreenState()


}

sealed class RecommendedState {

    object Initial : RecommendedState()

    object Pending : RecommendedState()

    object Error : RecommendedState()

    data class ShowRecommendedDish(
        val dishes: List<Dish>
    ) : RecommendedState()


}
