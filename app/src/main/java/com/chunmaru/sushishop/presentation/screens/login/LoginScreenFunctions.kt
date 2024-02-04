package com.chunmaru.sushishop.presentation.screens.login


import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.airbnb.lottie.compose.LottieConstants
import com.chunmaru.sushishop.data.api.ApiClient
import com.chunmaru.sushishop.data.api.NetworkResponse
import com.chunmaru.sushishop.data.api.controllers.ServiceControllerLoginDataImpl
import com.chunmaru.sushishop.data.models.login.LoginResponse
import com.chunmaru.sushishop.data.storage.DataStoreManager
import com.chunmaru.sushishop.domain.repositories.api_controller.ServiceControllerLoginData
import com.chunmaru.sushishop.presentation.screens.defaults.alerts.DefaultAlertDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


fun checkToken(
    dataStoreManager: DataStoreManager,
    coroutineScope: CoroutineScope,
    onSuccess: () -> Unit,
    onError: () -> Unit
) {
    coroutineScope.launch(Dispatchers.IO) {
        val token = dataStoreManager.getAdminToken()
        withContext(Dispatchers.Main) {
            if (token.isNotEmpty()) onSuccess()
            else onError()
        }
    }

}

fun checkLogin(
    serviceController: ServiceControllerLoginData = ServiceControllerLoginDataImpl(ApiClient.serviceApi),
    dataStoreManager: DataStoreManager,
    login: String,
    password: String,
    coroutinesScope: CoroutineScope,
    onState: (LoginState) -> Unit
) {
    if (login.isEmpty() || password.isEmpty()) {
        onState(LoginState.NoValidData)
        return
    }
    coroutinesScope.launch {
        onState(LoginState.Loading)
        when (val response = serviceController.login(login = login, password = password)) {

            is NetworkResponse.Error -> {
                pushWithMainContext { onState(LoginState.NoValidData) }
            }

            is NetworkResponse.Success<*> -> {
                val token = response.data as LoginResponse
                dataStoreManager.setAdminToken(token.token)
                pushWithMainContext { onState(LoginState.SuccessLogIn) }
            }

        }

    }
}

suspend fun pushWithMainContext(block: () -> Unit) {
    withContext(Dispatchers.Main) { block() }
}


fun handleTokenCheckError(): LoginScreenState =
    LoginScreenState.ShowData(password = "", login = "")

@Composable
fun HandleLoginState(
    loginState: MutableState<LoginState>,
    onDismiss: () -> Unit,
    navigateOnAdmin: () -> Unit
) {


    when (loginState.value) {
        LoginState.Loading -> {
            DefaultAlertDialog(
                iteration = LottieConstants.IterateForever,
                animAssets = "loading_anim.json",
                message = "Loading\nPlease wait",
                showDialog = true,
                onDismiss = {}
            )
        }

        LoginState.NoValidData -> {
            DefaultAlertDialog(
                animAssets = "error_anim.json",
                message = "Some your data is not valid\n" +
                        "Please fix that",
                showDialog = true,
                onDismiss = { onDismiss() }
            )
        }

        LoginState.SuccessLogIn -> {
            DefaultAlertDialog(
                iteration = 1,
                animAssets = "success_anim.json",
                message = "The login has been\n" +
                        "successfully processed",
                showDialog = true,
                onDismiss = {
                    navigateOnAdmin()
                }
            )
        }

        else -> Unit
    }
}


