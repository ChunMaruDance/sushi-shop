package com.chunmaru.sushishop.presentation.screens.add_render_dish

import androidx.lifecycle.ViewModel
import com.chunmaru.sushishop.data.api.ServiceController
import com.chunmaru.sushishop.data.models.dishes.Dish
import com.chunmaru.sushishop.data.models.dishes.DishWithIngredients
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddDishScreenViewModel @Inject constructor(
    private val serviceController: ServiceController
) : ViewModel() {

    private val _state = MutableStateFlow<ScreenState<DishWithIngredients>>(
        ScreenState.Success(
            DishWithIngredients(
                Dish(
                    id = 0,
                    name = "",
                    descriptions = "",
                    price = 0f,
                    discount = 0f,
                    image = byteArrayOf(),
                    weight = 0f
                ),
                listOf()
            )


        )
    )
    val state = _state.asStateFlow()


}