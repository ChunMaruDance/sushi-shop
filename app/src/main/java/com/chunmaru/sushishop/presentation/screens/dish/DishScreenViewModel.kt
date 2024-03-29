package com.chunmaru.sushishop.presentation.screens.dish

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.chunmaru.sushishop.data.api.ServiceApi
import com.chunmaru.sushishop.data.models.dishes.Dish
import com.chunmaru.sushishop.data.models.dishes.DishCounterWithIngredients
import com.chunmaru.sushishop.data.models.dishes.DishWithCounter
import com.chunmaru.sushishop.domain.navigation.NavigationEntryKey
import com.chunmaru.sushishop.domain.navigation.NavigationStackController
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DishScreenViewModel @Inject constructor(
    private val serviceApi: ServiceApi,
    private val navigationStackController: NavigationStackController
) : ViewModel() {

    private lateinit var navController: NavController

    private val _state = MutableStateFlow<ScreenState<DishCounterWithIngredients>>(ScreenState.Initial())
    val state = _state.asStateFlow()
    fun initController(navController: NavController) {
        this.navController = navController
    }

    fun getPreviewBackStackData() {

        //todo get ingredients db

        _state.value = ScreenState.Pending()
        navigationStackController.getPreviewBackStackData<Dish>(
            navController = navController,
            bundleKey = NavigationEntryKey.bundleDishKey,
            valueKey = NavigationEntryKey.argumentDishKey
        )?.let { dish ->
            _state.value = ScreenState.Success(
                DishCounterWithIngredients(
                    dishWithCounter = DishWithCounter(dish, 1),
                    ingredients = listOf()
                )
            )
        }

    }

    fun putInCurrentBackStack(
        onSuccess: () -> Unit
    ) {
        val successState = _state.value as ScreenState.Success? ?: return

        navigationStackController.putInCurrentBackStack(
            navController = navController,
            data = arrayListOf(successState.data.dishWithCounter),
            bundleKey = NavigationEntryKey.bundleDishesWithCounterKey,
            valueKey = NavigationEntryKey.argumentDishCounterKey
        )
        onSuccess()
    }

    fun addCounter() {
        val successState = _state.value as ScreenState.Success? ?: return

        _state.value = successState.copy(
            data = successState.data.copy(
                successState.data.dishWithCounter.copy(
                    counter = successState.data.dishWithCounter.counter + 1
                )
            )
        )

    }

    fun removeCounter() {
        val successState = _state.value as ScreenState.Success? ?: return

        _state.value = successState.copy(
            data = successState.data.copy(
                dishWithCounter = successState.data.dishWithCounter.copy(
                    counter = successState.data.dishWithCounter.counter - 1
                )
            )
        )

    }


}