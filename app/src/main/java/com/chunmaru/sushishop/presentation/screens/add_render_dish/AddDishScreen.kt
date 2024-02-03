package com.chunmaru.sushishop.presentation.screens.add_render_dish

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chunmaru.sushishop.data.models.dishes.DishWithIngredients
import com.chunmaru.sushishop.data.models.dishes.DishWithIngredientsCategories
import com.chunmaru.sushishop.data.readBytesFromUri
import com.chunmaru.sushishop.presentation.screens.add_render_dish.elements.DishImageWithIngredientsRender
import com.chunmaru.sushishop.presentation.screens.defaults.DefaultProgressBar
import com.chunmaru.sushishop.presentation.screens.defaults.DefaultTopBar
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState
import com.chunmaru.sushishop.presentation.screens.dish.elements.DishImageWithIngredients

@Composable
fun AddDishScreen(
    navController: NavController
) {

    val viewModel: AddDishScreenViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current


    when (val currentState = state.value) {
        is ScreenState.Initial -> Unit
        is ScreenState.Pending -> {
            DefaultProgressBar()
        }

        is ScreenState.Success -> {
            AddDishScreenContent(
                dishWithIngredients = currentState.data,
                onBackClick = { navController.popBackStack() },
                onImageChange = { viewModel.changeImage(it.readBytesFromUri(context)) },
                onTittleChange = { viewModel.tittleChange(it) },
                onCategoryChange = {viewModel.categoryChange(it)}
            )
        }
    }

}

@Composable
private fun AddDishScreenContent(
    dishWithIngredients: DishWithIngredientsCategories,
    onBackClick: () -> Unit,
    onImageChange: (Uri) -> Unit,
    onTittleChange: (String) -> Unit,
    onCategoryChange: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground)
    ) {
        DefaultTopBar(
            title = "Dish",
            onBackClick = { onBackClick() },
            onMoreClick = { },
        )

        DishImageWithIngredientsRender(
            dishWithIngredients = dishWithIngredients,
            onIngredientClick = {},
            onLoadMoreIngredients = {},
            onImageSelected = onImageChange,
            onCategoryChange = onCategoryChange,
            onTittleChange = onTittleChange
        )


    }


}
