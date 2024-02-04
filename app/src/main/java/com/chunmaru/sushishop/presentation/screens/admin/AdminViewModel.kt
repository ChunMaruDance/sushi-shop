package com.chunmaru.sushishop.presentation.screens.admin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunmaru.sushishop.data.api.NetworkResponse
import com.chunmaru.sushishop.data.models.admin.Admin
import com.chunmaru.sushishop.data.models.admin.toAdmin
import com.chunmaru.sushishop.data.storage.DataStoreManager
import com.chunmaru.sushishop.domain.repositories.api_controller.ServiceControllerLoginData
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val serviceApi: ServiceControllerLoginData,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {


    private val _state = MutableStateFlow<ScreenState<Admin>>(ScreenState.Initial())
    val state = _state.asStateFlow()

    init {
        init()
    }


    private fun init() {
        _state.value = ScreenState.Pending()
        viewModelScope.launch(Dispatchers.IO) {
            val token = dataStoreManager.getAdminToken()
            when (val response = serviceApi.getAdminProfile(token)) {
                is NetworkResponse.Error -> {}
                is NetworkResponse.Success -> {
                    Log.d("MyTag", "init: ${response.data} ")
                    _state.value = ScreenState.Success(response.data.toAdmin())
                }
            }

        }

    }

    fun logout(onSuccess: () -> Unit) {
        viewModelScope.launch {
            dataStoreManager.removeAdminToken()
            withContext(Dispatchers.Main) { onSuccess() }
        }

    }


}