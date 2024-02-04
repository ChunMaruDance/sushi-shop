package com.chunmaru.sushishop.presentation.screens.add_render_dish


import androidx.lifecycle.ViewModel
import com.chunmaru.sushishop.data.models.dishes.Dish
import com.chunmaru.sushishop.data.models.dishes.DishWithIngredientsCategories
import com.chunmaru.sushishop.domain.repositories.api_controller.ServiceControllerDish
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddDishScreenViewModel @Inject constructor(
    private val serviceController: ServiceControllerDish
) : ViewModel() {

    private val _state = MutableStateFlow<ScreenState<DishWithIngredientsCategories>>(
        ScreenState.Success(
            DishWithIngredientsCategories(
                Dish(
                    id = 0,
                    name = "",
                    descriptions = "",
                    price = 0f,
                    discount = 0f,
                    image = byteArrayOf(),
                    weight = 0f,
                    category = ""
                ),
                listOf(),
                listOf()
            )


        )
    )
    val state = _state.asStateFlow()

    init {
        init()
    }

    private fun init() {

    }

    private inline fun updateDish(mutator: Dish.() -> Dish) {
        val currentState = _state.value as? ScreenState.Success ?: return
        _state.value =
            currentState.copy(data = currentState.data.copy(dish = currentState.data.dish.mutator()))
    }

    fun changeImage(image: ByteArray) {
        updateDish { copy(image = image) }
    }

    fun tittleChange(title: String) {
        updateDish { copy(name = title) }
    }

    fun categoryChange(category: String) {
        updateDish { copy(category = category) }
    }


}