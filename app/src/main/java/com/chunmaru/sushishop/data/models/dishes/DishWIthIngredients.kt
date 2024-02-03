package com.chunmaru.sushishop.data.models.dishes

import com.chunmaru.sushishop.data.models.Category


data class DishCounterWithIngredients(
    val dishWithCounter: DishWithCounter,
    val ingredients: List<Ingredient>
)


data class DishWithIngredientsCategories(
    val dish: Dish,
    val ingredients: List<IngredientSelect>,
    val categories: List<Category>
)

data class DishWithIngredients(
    val dish: Dish,
    val ingredients: List<IngredientSelect>
)

data class IngredientSelect(
    val ingredient: Ingredient,
    val select: Boolean,
)
