package com.chunmaru.sushishop.presentation.screens.add_render_ingredient


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.chunmaru.sushishop.data.models.dishes.Ingredient
import com.chunmaru.sushishop.presentation.screens.add_render_ingredient.elements.AddRenderIngredientsScaffoldContent
import com.chunmaru.sushishop.presentation.screens.add_render_ingredient.elements.AddRenderIngredientsTopBar
import com.chunmaru.sushishop.presentation.screens.defaults.alerts.AlertSaveHandler
import com.chunmaru.sushishop.presentation.screens.add_render_ingredient.elements.IngredientsAlertHandler
import com.chunmaru.sushishop.presentation.screens.defaults.DefaultProgressBar
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState
import com.chunmaru.sushishop.presentation.screens.defaults.alerts.DefaultAlertsState

@Composable
fun AddRenderIngredientScreen(
    onBackClick: () -> Unit
) {

    val viewModel: AddRenderIngredientViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()

    when (val currentState = state.value) {
        is ScreenState.Initial -> Unit
        is ScreenState.Pending -> {
            DefaultProgressBar()
        }

        is ScreenState.Success -> {
            AddRenderIngredientScreenContent(
                state = currentState.data,
                onBackClick = onBackClick,
                onSaveIngredient = {
                    viewModel.saveIngredient(it)
                }
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddRenderIngredientScreenContent(
    state: List<Ingredient>,
    onBackClick: () -> Unit,
    onSaveIngredient: (AlertSaveHandler<Ingredient>) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            AddRenderIngredientsTopBar(
                scrollBehavior = scrollBehavior,
                onBackClick = onBackClick,
                tittle = "Ingredients"
            )
        }
    ) { paddingValues ->

        val alertsState: MutableState<DefaultAlertsState<Ingredient>> = remember {
            mutableStateOf(DefaultAlertsState.Initial())
        }

        AddRenderIngredientsScaffoldContent(
            paddingValues = paddingValues,
            ingredients = state,
            onSaveIngredient = { ingredient ->
                alertsState.value = DefaultAlertsState.Confirm(ingredient)
            }
        )
        IngredientsAlertHandler(
            state = alertsState.value,
            onCancel = { alertsState.value = DefaultAlertsState.Initial() },
            onConfirm = { ingredient ->
                alertsState.value = DefaultAlertsState.Loading()
                onSaveIngredient(object : AlertSaveHandler<Ingredient> {
                    override val data: Ingredient
                        get() = ingredient

                    override fun onSuccess() {
                        alertsState.value = DefaultAlertsState.Success()
                    }

                    override fun onError() {
                        alertsState.value = DefaultAlertsState.Error()
                    }

                })

            },
            onSuccess = {
                alertsState.value = DefaultAlertsState.Initial()
            },
            onError = {
                alertsState.value = DefaultAlertsState.Initial()
            }
        )

    }

}
