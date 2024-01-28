package com.chunmaru.sushishop.presentation.screens.login


import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.chunmaru.sushishop.data.storage.DataStoreManager
import com.chunmaru.sushishop.presentation.screens.login.elements.LoginScreenContent

@Composable
fun LoginScreen(
    navController: NavController,
    onAdminNavigate: () -> Unit
) {

    val state = remember<MutableState<LoginScreenState>> {
        mutableStateOf(LoginScreenState.Initial)
    }
    val coroutinesScope = rememberCoroutineScope()

    val loginState = remember<MutableState<LoginState>> {
        mutableStateOf(LoginState.Initial)
    }
    val context = LocalContext.current

    val dataStoreManager = remember {
        mutableStateOf(DataStoreManager(context))
    }


    when (val currentState = state.value) {
        LoginScreenState.Initial -> {
            checkToken(
                dataStoreManager = dataStoreManager.value,
                coroutineScope = coroutinesScope,
                onSuccess = { onAdminNavigate() },
                onError = { state.value = handleTokenCheckError() }
            )
        }

        LoginScreenState.Pending -> {
        }

        is LoginScreenState.ShowData -> {
            LoginScreenContent(
                password = currentState.password,
                login = currentState.login,
                passwordChange = { password ->
                    state.value = currentState.copy(password = password)
                },
                loginChange = { login ->
                    state.value = currentState.copy(login = login)
                },
                checkSignIn = {
                    checkLogin(
                        dataStoreManager = dataStoreManager.value,
                        login = currentState.login,
                        password = currentState.password,
                        coroutinesScope = coroutinesScope,
                        onState = { loginState.value = it }
                    )

                }
            )
            HandleLoginState(
                loginState = loginState,
                onDismiss = { loginState.value = LoginState.Initial },
                navigateOnAdmin = onAdminNavigate
            )


        }

    }


}

