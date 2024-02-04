package com.chunmaru.sushishop.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.chunmaru.sushishop.data.api.ServiceApi
import com.chunmaru.sushishop.data.models.dishes.Dish
import com.chunmaru.sushishop.data.models.dishes.DishWithCounter
import com.chunmaru.sushishop.domain.navigation.NavigationEntryKey
import com.chunmaru.sushishop.domain.navigation.NavigationStackController
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val serviceApi: ServiceApi,
    private val navigationStackController: NavigationStackController
) : ViewModel() {

    private var _state: MutableStateFlow<ScreenState<CategoryState>> =
        MutableStateFlow(ScreenState.Initial())
    val state = _state.asStateFlow()

    private lateinit var navController: NavController

    private val orders = MutableStateFlow<MutableList<DishWithCounter>>(mutableListOf())
    val ordersCount: StateFlow<Int> = orders.map { list -> list.sumOf { it.counter } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)

    fun addToOrder(dish: Dish) {
        orders.value = (orders.value.toMutableList() + DishWithCounter(dish, 1))
            .groupBy { it.dish.id }
            .map { (_, groupedItems) ->
                groupedItems.reduce { acc, item -> acc.copy(counter = acc.counter + item.counter) }
            }.toMutableList()
    }

    fun initController(navController: NavController) {
        this.navController = navController
        getCurrentBackStackEntry()?.toMutableList()?.let {
            orders.value = it
        }
    }

    private fun getCurrentBackStackEntry(): List<DishWithCounter>? {
        return navigationStackController.getCurrentBackStackDataArray(
            navController = navController,
            bundleKey = NavigationEntryKey.bundleOrder,
            valueKey = NavigationEntryKey.argumentOrder
        )
    }

    fun putOrderInCurrentBackStack(
        onSuccess: () -> Unit
    ) {
        val list = arrayListOf<DishWithCounter>()
        list.addAll(orders.value)
        navigationStackController.putInCurrentBackStack(
            navController = navController,
            data = list,
            bundleKey = NavigationEntryKey.bundleDishesWithCounterKey,
            valueKey = NavigationEntryKey.argumentDishCounterKey
        )
        onSuccess()
    }

    fun putDishInCurrentBackStack(
        dish: Dish,
        onSuccess: () -> Unit
    ) {
        navigationStackController.putInCurrentBackStack(
            navController = navController,
            data = dish,
            bundleKey = NavigationEntryKey.bundleDishKey,
            valueKey = NavigationEntryKey.argumentDishKey
        )
        onSuccess()
    }

    fun initData() {

        viewModelScope.launch(Dispatchers.IO) {
            _state.value = ScreenState.Pending()

            val categoryState = CategoryState.ShowCategory(
                activeCategory = "All",
                dishes = listOf()//list
            )
            delay(2000)
            _state.value = ScreenState.Success(categoryState)
        }
    }


    fun changeCategory(category: String) {

        val categoryState = CategoryState.ShowCategory(
            activeCategory = category,
            dishes = listOf()
        )

        _state.value = ScreenState.Success(categoryState)
    }


}