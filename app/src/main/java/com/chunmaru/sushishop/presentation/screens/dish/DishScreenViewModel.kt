package com.chunmaru.sushishop.presentation.screens.dish

import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.chunmaru.sushishop.data.api.ServiceApi
import com.chunmaru.sushishop.data.models.dishes.DishWithCounter
import com.chunmaru.sushishop.data.models.dishes.TestDish
import com.chunmaru.sushishop.presentation.navigation.NavigationEntryKey
import com.chunmaru.sushishop.presentation.navigation.NavigationStackController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DishScreenViewModel @Inject constructor(
    private val serviceApi: ServiceApi,
    private val navigationStackController: NavigationStackController
) : ViewModel() {

    private lateinit var navController: NavController

    fun initController(navController: NavController) {
        this.navController = navController
    }

    fun getPreviewBackStackData(): List<TestDish>? {
        return navigationStackController.getPreviewBackStackData<TestDish>(
            navController = navController,
            bundleKey = NavigationEntryKey.bundleDishKey,
            valueKey = NavigationEntryKey.argumentDishKey
        )?.let { dish ->
            listOf(dish)
        }

    }

    fun putInCurrentBackStack(
        dish: DishWithCounter,
        onSuccess: () -> Unit
    ) {

        navigationStackController.putInCurrentBackStack(
            navController = navController,
            data = arrayListOf(dish),
            bundleKey = NavigationEntryKey.bundleDishesWithCounterKey,
            valueKey = NavigationEntryKey.argumentDishCounterKey
        )
        onSuccess()
    }


}