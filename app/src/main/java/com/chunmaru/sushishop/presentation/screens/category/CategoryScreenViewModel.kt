package com.chunmaru.sushishop.presentation.screens.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunmaru.sushishop.data.api.ServiceController
import com.chunmaru.sushishop.data.models.dishes.Dish
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryScreenViewModel @Inject constructor(
    private val serviceController: ServiceController
) : ViewModel() {

    private val _state = MutableStateFlow<ScreenState<List<Dish>>>(ScreenState.Initial())
    val state = _state.asStateFlow()

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = ScreenState.Pending()
            //todo
            serviceController.getDishesByCategory("category")

        }
    }


}