package com.chunmaru.sushishop.presentation.navigation

import androidx.compose.runtime.Composable
import com.chunmaru.sushishop.presentation.screens.add_render_dish.AddDishScreen
import com.chunmaru.sushishop.presentation.screens.admin.AdminScreen
import com.chunmaru.sushishop.presentation.screens.category.CategoryScreen
import com.chunmaru.sushishop.presentation.screens.completed_orders.CompletedOrders
import com.chunmaru.sushishop.presentation.screens.dish.DishScreen
import com.chunmaru.sushishop.presentation.screens.home.HomeScreen
import com.chunmaru.sushishop.presentation.screens.login.LoginScreen
import com.chunmaru.sushishop.presentation.screens.management_menu.ManagementMenuScreen
import com.chunmaru.sushishop.presentation.screens.map.MapScreen
import com.chunmaru.sushishop.presentation.screens.order.OrderScreen
import com.chunmaru.sushishop.presentation.screens.order_details.OrderDetailsScreen
import com.chunmaru.sushishop.presentation.screens.search.SearchScreen
import com.chunmaru.sushishop.presentation.screens.splash.SplashScreen

@Composable
fun ScreenNavigator() {

    val navigationState = rememberNavigationState()

    NavigationGraph(
        navHostController = navigationState.navHostController,
        onHome = {
            HomeScreen(
                navigationState.navHostController,
                onDishNavigate = { navigationState.navigate(Screen.DishScreen.route) },
                onOrderNavigate = { navigationState.navigate(Screen.OrderScreen.route) },
                onLoginNavigate = { navigationState.navigate(Screen.LoginScreen.route) },
                onSearchNavigate = { navigationState.navigate(Screen.SearchScreen.route) }
            )
        },
        onSplash = {
            SplashScreen(
                onHome = { navigationState.navigate(Screen.HomeScreen.route) }
            )
        },
        onDish = {
            DishScreen(
                navController = navigationState.navHostController,
                onBuyClick = { navigationState.navigate(Screen.OrderScreen.route) }
            )
        },
        onOrder = {
            OrderScreen(
                navController = navigationState.navHostController,
                onOrderDetails = { navigationState.navigate(Screen.OrderDetailsScreen.route) }
            )
        },
        onOrderDetails = {
            OrderDetailsScreen(navController = navigationState.navHostController,
                onMapScreen = { navigationState.navigate(Screen.MapScreen.route) },
                onCompleteOrder = { navigationState.navigateTo(Screen.CompleteOrdersScreen.route) })
        },
        onMap = {
            MapScreen(navController = navigationState.navHostController)
        },
        onCompleteOrders = {
            CompletedOrders()
        },
        onLogin = {
            LoginScreen(
                navController = navigationState.navHostController,
                onAdminNavigate = { navigationState.navigateTo(Screen.AdminScreen.route) }
            )
        },
        onAdmin = {
            AdminScreen(
                onManagementMenuNavigate = { navigationState.navigate(Screen.ManagementMenuScreen.route) },
                onBackNavigate = { navigationState.navHostController.popBackStack() },
                onChangeProfileNavigate = {},
                onOrdersHistoryNavigate = {},
                onActiveOrdersNavigate = {},
                onStatisticsNavigate = {}
            )
        },
        onManagementMenu = {
            ManagementMenuScreen(
                onBackClick = { navigationState.navHostController.popBackStack() },
                onAddRenderDish = { navigationState.navigate(Screen.AddRenderDishScreen.route) }
            )
        },
        onSearch = {
            SearchScreen()
        },
        onCategory = {
            CategoryScreen()
        },
        onAddRenderDish = {
            AddDishScreen(navController = navigationState.navHostController)
        }
    )

}