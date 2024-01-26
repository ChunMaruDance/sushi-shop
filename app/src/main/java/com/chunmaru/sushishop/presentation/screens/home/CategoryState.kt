package com.chunmaru.sushishop.presentation.screens.home


import com.chunmaru.sushishop.data.models.dishes.TestDish


sealed class CategoryState {

    object Initial : CategoryState()

    class PendingCategory(
        val activeCategory: String
    ) : CategoryState()

    data class ShowCategory(
        val activeCategory: String,
        val dishes: List<TestDish>
    ) : CategoryState()


}
