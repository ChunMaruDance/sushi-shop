package com.chunmaru.sushishop.presentation.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(
    navHostController: NavHostController,
    onHome: @Composable () -> Unit,
    onSplash: @Composable () -> Unit,
    onDish: @Composable () -> Unit,
    onOrder: @Composable () -> Unit,
    onOrderDetails: @Composable () -> Unit,
    onMap: @Composable () -> Unit,
    onCompleteOrders: @Composable () -> Unit,
    onLogin: @Composable () -> Unit,
    onAdmin: @Composable () -> Unit,
    onManagementMenu: @Composable () -> Unit,
    onSearch: @Composable () -> Unit,
    onCategory: @Composable () -> Unit,
    onAddRenderDish: @Composable () -> Unit,
    onAddRenderIngredient: @Composable () -> Unit
) {

    NavHost(
        navController = navHostController,
        startDestination = Screen.SplashScreen.route
    ) {

        composable(route = Screen.HomeScreen.route) { onHome() }
        composable(route = Screen.DishScreen.route) { onDish() }
        composable(route = Screen.SplashScreen.route) { onSplash() }
        composable(route = Screen.OrderScreen.route) { onOrder() }
        composable(route = Screen.OrderDetailsScreen.route) { onOrderDetails() }
        composable(route = Screen.MapScreen.route) { onMap() }
        composable(route = Screen.CompleteOrdersScreen.route) { onCompleteOrders() }
        composable(route = Screen.LoginScreen.route) { onLogin() }
        composable(route = Screen.AdminScreen.route) { onAdmin() }
        composable(route = Screen.ManagementMenuScreen.route) { onManagementMenu() }
        composable(route = Screen.SearchScreen.route) { onSearch() }
        composable(route = Screen.CategoryScreen.route) { onCategory() }
        composable(route = Screen.AddRenderDishScreen.route) { onAddRenderDish() }
        composable(route = Screen.AddRenderIngredientScreen.route) { onAddRenderIngredient() }
    }

}

fun NavController.navigate(
    route: String,
    param: Bundle?,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    this.currentBackStackEntry?.arguments?.putAll(param)
    navigate(route, builder)
}

