package com.chunmaru.sushishop.presentation.screens.search


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunmaru.sushishop.data.api.NetworkResponse
import com.chunmaru.sushishop.data.api.ServerNotResponse
import com.chunmaru.sushishop.data.models.dishes.DishResponse.Companion.toDishList
import com.chunmaru.sushishop.domain.repositories.api_controller.ServiceControllerDish
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val serviceController: ServiceControllerDish
) : ViewModel() {

    private val _state =
        MutableStateFlow<SearchScreenState>(
            SearchScreenState.ShowData(
                recommended = RecommendedState.Initial,
                listOf()
            )
        )
    val state = _state.asStateFlow()

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentState = _state.value as SearchScreenState.ShowData? ?: return@launch

            when (val response = serviceController.getRecommendedDishes()) {
                is NetworkResponse.Error -> {
                    if (response.e is ServerNotResponse) {
                        _state.value = SearchScreenState.ServerNotResponse
                    } else {
                        _state.value = currentState.copy(recommended = RecommendedState.Error)
                    }

                }

                is NetworkResponse.Success -> {
                    _state.value = currentState.copy(
                        recommended = RecommendedState.ShowRecommendedDish(response.data.toDishList())
                    )
                }
            }

        }
    }

    private fun search(query: String) {
        //todo search
    }


}
