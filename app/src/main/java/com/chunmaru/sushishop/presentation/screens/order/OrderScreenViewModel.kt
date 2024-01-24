package com.chunmaru.sushishop.presentation.screens.order


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.chunmaru.sushishop.data.models.dishes.DishWithCounter
import com.chunmaru.sushishop.presentation.navigation.NavigationEntryKey
import com.chunmaru.sushishop.presentation.navigation.NavigationStackController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OrderScreenViewModel @Inject constructor(
    private val navigationStackController: NavigationStackController
) : ViewModel() {

    private lateinit var navController: NavController

    private val _state: MutableStateFlow<OrderScreenState> =
        MutableStateFlow(OrderScreenState.Initial)
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
            _state.value = OrderScreenState.ShowData(dishes)
        }
    }


    fun putInCurrentBackStackToOrderDetails(
        onSuccess: () -> Unit
    ) {

        val currentState = _state.value as OrderScreenState.ShowData

        val list = arrayListOf<DishWithCounter>()
        list.addAll(currentState.dishesWithCounters)

        navigationStackController.putInCurrentBackStack(
            navController = navController,
            data = list,
            bundleKey = NavigationEntryKey.bundleOrderPrice,
            valueKey = NavigationEntryKey.argumentOrderPrice
        )
        onSuccess()
    }

    fun addCounter(dishCounter: DishWithCounter) {
        val currentState = _state.value as? OrderScreenState.ShowData ?: return

        val updatedDishesWithCounters = currentState.dishesWithCounters.map {
            if (it.dish.id == dishCounter.dish.id) {
                it.copy(counter = it.counter + 1)
            } else {
                it
            }
        }
        _state.value = currentState.copy(dishesWithCounters = updatedDishesWithCounters)
    }

    fun removeDish(dishCounter: DishWithCounter) {
        val currentState = _state.value as? OrderScreenState.ShowData ?: return

        val updatedDishesWithCounters = currentState.dishesWithCounters.filter {
            it.dish.id != dishCounter.dish.id
        }

        _state.value = currentState.copy(dishesWithCounters = updatedDishesWithCounters)
    }

    fun putInPreviewBackStackToHome(
        onSuccess: () -> Unit
    ) {

        val currentState = _state.value as OrderScreenState.ShowData

        val list = arrayListOf<DishWithCounter>()
        list.addAll(currentState.dishesWithCounters)
        navigationStackController.putInPreviewBackStack(
            navController = navController,
            data = list,
            bundleKey = NavigationEntryKey.bundleOrder,
            valueKey = NavigationEntryKey.argumentOrder
        )
        onSuccess()


    }

}