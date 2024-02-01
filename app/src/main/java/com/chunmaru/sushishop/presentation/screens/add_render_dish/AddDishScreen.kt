package com.chunmaru.sushishop.presentation.screens.add_render_dish

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chunmaru.sushishop.data.models.dishes.Dish
import com.chunmaru.sushishop.data.models.dishes.DishWithIngredients
import com.chunmaru.sushishop.presentation.screens.defaults.DefaultProgressBar
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState

@Composable
fun AddDishScreen(
    navController: NavController
) {

    val viewModel: AddDishScreenViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()

    when (val currentState = state.value) {
        is ScreenState.Initial -> Unit
        is ScreenState.Pending -> {
            DefaultProgressBar()
        }

        is ScreenState.Success -> {
            AddDishScreenContent(
                dish = currentState.data,
                onBackClick = {}
            )
        }
    }

}

@Composable
private fun AddDishScreenContent(
    dish: DishWithIngredients,
    onBackClick: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground)
    ) {
        //todo screen
    }


}
