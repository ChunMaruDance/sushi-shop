package com.chunmaru.sushishop.presentation.screens.order_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.chunmaru.sushishop.data.format
import com.chunmaru.sushishop.data.models.dishes.DishWithCounter
import com.chunmaru.sushishop.data.storage.DataStoreManager
import com.chunmaru.sushishop.presentation.navigation.NavigationEntryKey
import com.chunmaru.sushishop.presentation.navigation.NavigationStackController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OrderDetailsScreenViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val navigationStackController: NavigationStackController
) : ViewModel() {

    private val _state: MutableStateFlow<OrderDetailsScreenState> =
        MutableStateFlow(OrderDetailsScreenState.Initial)

    val state = _state.asStateFlow()

    private lateinit var navController: NavController

    fun initController(navController: NavController) {
        this.navController = navController
    }

    fun initState() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = OrderDetailsScreenState.Pending

            val user = dataStoreManager.getLocaleUserData()

            val orders = getPreviewBackStackData() ?: listOf()

            val price = orders.sumOf { it.dish.price.toDouble() * it.counter }.format(4)

            _state.value = OrderDetailsScreenState.ShowData(
                price = price,
                usersData = user,
                order = orders
            )

        }
    }

    private fun getPreviewBackStackData(): List<DishWithCounter>? {
        return navigationStackController.getPreviewBackStackDataArray(
            navController = navController,
            bundleKey = NavigationEntryKey.bundleOrderPrice,
            valueKey = NavigationEntryKey.argumentOrderPrice
        )

    }

    fun orderComplete(
        userName: String,
        location: String,
        phoneNumber: String,
        onState: (OrderState) -> Unit,
    ) {

        if (location.isEmpty() || userName.isEmpty() || phoneNumber.isEmpty()) {
            onState(OrderState.NoValidData)
            return
        }

        onState(OrderState.Loading)


        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            val currentState = _state.value as OrderDetailsScreenState.ShowData

            if (false) {
                onState(OrderState.ShortageGoods)
                return@launch
            }


            if (userName != currentState.usersData.username) {
                dataStoreManager.saveUserName(userName)
            }

            if (!currentState.usersData.addresses.contains(location) && location.isNotEmpty()) {
                dataStoreManager.saveAddress(location)
            }

            if (phoneNumber != currentState.usersData.phone) {
                dataStoreManager.savePhoneNumber(phoneNumber)
            }

            withContext(Dispatchers.Main) { onState(OrderState.SuccessOrder) }

        }


    }

    fun deleteLocation(location: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val currentState = _state.value as? OrderDetailsScreenState.ShowData
            currentState?.let {
                dataStoreManager.deleteAddress(location)

                val updatedAddresses = currentState.usersData.addresses.toMutableList().apply {
                    remove(location)
                }
                val updatedState =
                    currentState.copy(usersData = currentState.usersData.copy(addresses = updatedAddresses))

                _state.value = updatedState
            }
        }

    }

}


//
//val price = orders.value
//    .sumOf {
//        it.dish.price.toDouble() * it.counter
//    }
//    .format(4)