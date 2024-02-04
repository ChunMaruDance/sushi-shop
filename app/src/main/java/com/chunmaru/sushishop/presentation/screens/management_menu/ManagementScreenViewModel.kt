package com.chunmaru.sushishop.presentation.screens.management_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunmaru.sushishop.data.api.NetworkResponse
import com.chunmaru.sushishop.data.models.dishes.Dish
import com.chunmaru.sushishop.domain.repositories.api_controller.ServiceControllerDish
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManagementScreenViewModel @Inject constructor(
    private val serviceControllerDish: ServiceControllerDish,
) : ViewModel() {

    private val _state = MutableStateFlow<ScreenState<Dish?>>(ScreenState.Initial())
    val state = _state.asStateFlow()

    init {
        init()
    }

    private fun init() {
        _state.value = ScreenState.Pending()
        viewModelScope.launch(Dispatchers.IO) {
            when (val responseSpecialDish = serviceControllerDish.getSpecialDish()) {
                is NetworkResponse.Success -> {
                    _state.value = ScreenState.Success(responseSpecialDish.data.toDish())
                }

                else -> {
                    _state.value = ScreenState.Success(null)
                }
            }


        }
    }


}


