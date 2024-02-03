package com.chunmaru.sushishop.presentation.screens.add_render_ingredient

import androidx.lifecycle.ViewModel
import com.chunmaru.sushishop.data.api.ServiceController
import com.chunmaru.sushishop.data.models.dishes.Dish
import com.chunmaru.sushishop.data.models.dishes.Ingredient
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddRenderIngredientViewModel @Inject constructor(
    private val serviceController: ServiceController
) : ViewModel() {

    private val _state =
        MutableStateFlow<ScreenState<CreatedIngredientWithAllIngredients>>(ScreenState.Initial())
    val state = _state.asStateFlow()

    init {
        _state.value = ScreenState.Success(
            CreatedIngredientWithAllIngredients(
                ingredient = Ingredient(
                    name = "",
                    descriptions = "",
                    id = 0,
                    img = byteArrayOf()
                ),
                listOf()
            )
        )
    }


    private inline fun updateDish(mutator: Ingredient.() -> Ingredient) {
        val currentState = _state.value as? ScreenState.Success ?: return
        _state.value =
            currentState.copy(data = currentState.data.copy(ingredient = currentState.data.ingredient.mutator()))
    }

    fun changeImage(image: ByteArray) {
        updateDish { copy(img = image) }
    }


}

data class CreatedIngredientWithAllIngredients(
    val ingredient: Ingredient,
    val allIngredients: List<Ingredient>
)
