package com.chunmaru.sushishop.presentation.navigation

sealed class Screen(val route: String) {

    object SplashScreen : Screen("splash")

    object HomeScreen : Screen("home")

    object DishScreen : Screen("dish")

    object OrderScreen : Screen("order")

    object OrderDetailsScreen : Screen("order details")

    object MapScreen : Screen("map")

    object CompleteOrdersScreen : Screen("complete orders")

    object LoginScreen : Screen("login")

    object AdminScreen : Screen("admin")

    object ManagementMenuScreen : Screen("menu management")

    object SearchScreen : Screen("search")

    object CategoryScreen : Screen("category")

    object AddRenderDishScreen : Screen("add render dish")

    object AddRenderIngredientScreen : Screen("add render ingredient")


}