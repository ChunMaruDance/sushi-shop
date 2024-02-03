package com.chunmaru.sushishop.data.models.dishes


data class DishCounterWithIngredients(
    val dishWithCounter: DishWithCounter,
    val ingredients: List<Ingredient>
)

data class DishWithIngredients(
    val dish: Dish,
    val ingredients: List<Ingredient>
)