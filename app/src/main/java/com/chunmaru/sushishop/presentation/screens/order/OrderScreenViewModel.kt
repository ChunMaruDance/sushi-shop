package com.chunmaru.sushishop.presentation.screens.order


import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.chunmaru.sushishop.data.models.dishes.DishWithCounter
import com.chunmaru.sushishop.presentation.navigation.NavigationEntryKey
import com.chunmaru.sushishop.presentation.navigation.NavigationStackController
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OrderScreenViewModel @Inject constructor(
    private val navigationStackController: NavigationStackController
) : ViewModel() {

    private lateinit var navController: NavController

    private val _state: MutableStateFlow<ScreenState<List<DishWithCounter>>> =
        MutableStateFlow(ScreenState.Initial())
    val state = _state.asStateFlow()


    fun initController(navController: NavController) {
        this.navController = navController
    }


    fun getPreviewBackStackData() {
        navigationStackController.getPreviewBackStackDataArray<DishWithCounter>(
            navController = navController,
            bundleKey = NavigationEntryKey.bundleDishesWithCounterKey,
            valueKey = NavigationEntryKey.argumentDishCounterKey
        )?.also { dishes ->
            _state.value = ScreenState.Success(dishes)
        }
    }


    fun putInCurrentBackStackToOrderDetails(
        onSuccess: () -> Unit
    ) {

        val currentState = _state.value as ScreenState.Success

        val list = arrayListOf<DishWithCounter>()
        list.addAll(currentState.data)

        navigationStackController.putInCurrentBackStack(
            navController = navController,
            data = list,
            bundleKey = NavigationEntryKey.bundleOrderPrice,
            valueKey = NavigationEntryKey.argumentOrderPrice
        )
        onSuccess()
    }

    fun addCounter(dishCounter: DishWithCounter) {
        val currentState = _state.value as? ScreenState.Success ?: return

        val updatedDishesWithCounters = currentState.data.map {
            if (it.dish.id == dishCounter.dish.id) {
                it.copy(counter = it.counter + 1)
            } else {
                it
            }
        }
        _state.value = currentState.copy(updatedDishesWithCounters)
    }

    fun removeDish(dishCounter: DishWithCounter) {
        val currentState = _state.value as? ScreenState.Success ?: return

        val updatedDishesWithCounters = currentState.data.filter {
            it.dish.id != dishCounter.dish.id
        }

        _state.value = currentState.copy(updatedDishesWithCounters)
    }

    fun putInPreviewBackStackToHome(
        onSuccess: () -> Unit
    ) {

        val currentState = _state.value as ScreenState.Success

        val list = arrayListOf<DishWithCounter>()
        list.addAll(currentState.data)
        navigationStackController.putInPreviewBackStack(
            navController = navController,
            data = list,
            bundleKey = NavigationEntryKey.bundleOrder,
            valueKey = NavigationEntryKey.argumentOrder
        )
        onSuccess()


    }

}