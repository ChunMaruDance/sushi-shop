package com.chunmaru.sushishop.data.models.dishes


data class DishCounterWithIngredients(
    val dishWithCounter: DishWithCounter,
    val ingredientsData: List<IngredientsData>
)

data class DishWithIngredients(
    val dish: Dish,
    val ingredientsData: List<IngredientsData>
)