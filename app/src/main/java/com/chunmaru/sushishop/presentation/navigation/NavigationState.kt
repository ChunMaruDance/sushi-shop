package com.chunmaru.sushishop.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationState(
    val navHostController: NavHostController
) {
    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(Screen.HomeScreen.route) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

    }

    fun navigate(route: String) {
        navHostController.navigate(route)
    }


}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController = navHostController)
    }

}