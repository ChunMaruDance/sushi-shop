package com.chunmaru.sushishop.presentation.screens.add_render_ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunmaru.sushishop.data.models.dishes.Ingredient
import com.chunmaru.sushishop.domain.repositories.api_controller.ServiceControllerIngredients
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState
import com.chunmaru.sushishop.presentation.screens.defaults.alerts.AlertSaveHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddRenderIngredientViewModel @Inject constructor(
    private val serviceController: ServiceControllerIngredients
) : ViewModel() {

    private val _state =
        MutableStateFlow<ScreenState<List<Ingredient>>>(ScreenState.Initial())
    val state = _state.asStateFlow()

    init {
        _state.value = ScreenState.Success(listOf())
    }

    fun saveIngredient(alertSaveHandler: AlertSaveHandler<Ingredient>) {
        viewModelScope.launch {
            delay(2000)
            withContext(Dispatchers.Main) { alertSaveHandler.onSuccess() }
        }

    }


}

data class CreatedIngredientWithAllIngredients(
    val ingredient: Ingredient,
    val allIngredients: List<Ingredient>
)
