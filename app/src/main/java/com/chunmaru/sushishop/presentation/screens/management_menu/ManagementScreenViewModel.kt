package com.chunmaru.sushishop.presentation.screens.management_menu

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunmaru.sushishop.data.api.NetworkResponse
import com.chunmaru.sushishop.data.api.ServiceController
import com.chunmaru.sushishop.data.models.Category
import com.chunmaru.sushishop.data.models.CategoryResponse
import com.chunmaru.sushishop.data.models.CategoryResponse.CREATOR.toCategoriesList
import com.chunmaru.sushishop.data.models.dishes.Dish
import com.chunmaru.sushishop.data.models.dishes.DishResponse
import com.chunmaru.sushishop.data.models.dishes.TestDish
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManagementScreenViewModel @Inject constructor(
    private val serviceController: ServiceController
) : ViewModel() {

    private val _state = MutableStateFlow<ScreenState<ManagementData>>(ScreenState.Initial())
    val state = _state.asStateFlow()


    init {
        init()
    }

    private fun init() {
        _state.value = ScreenState.Pending()
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)

            val responseSpecialDish = serviceController.getSpecialDish()
            val responseCategories = serviceController.getCategories()

            var categories = listOf<Category>()
            var specialDish: Dish? = null;

            when (responseCategories) {
                is NetworkResponse.Error -> {
                    Log.d("MyTag", "initCategories: error ")
                }

                is NetworkResponse.Success -> {
                    categories = responseCategories.data.toCategoriesList()
                }
            }

            when (responseSpecialDish) {
                is NetworkResponse.Success -> {
                    specialDish = responseSpecialDish.data.toDish()
                }

                else -> Unit
            }


            Log.d("MyTag", "init: $categories ")
            Log.d("MyTag", "init: $specialDish ")

            _state.value = ScreenState.Success(
                ManagementData(
                    specialDish = specialDish,
                    allCategory = categories
                )
            )


        }
    }


}


data class ManagementData(
    val specialDish: Dish?,
    val allCategory: List<Category>
)
