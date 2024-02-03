package com.chunmaru.sushishop.presentation.screens.home


import com.chunmaru.sushishop.data.models.dishes.Dish


sealed class CategoryState {

    object Initial : CategoryState()

    class PendingCategory(
        val activeCategory: String
    ) : CategoryState()

    data class ShowCategory(
        val activeCategory: String,
        val dishes: List<Dish>
    ) : CategoryState()


}
