package com.chunmaru.sushishop.presentation.screens.login

sealed class LoginScreenState {

    object Initial : LoginScreenState()

    object Pending : LoginScreenState()

    data class ShowData(
        val login: String,
        val password: String
    ) : LoginScreenState()

}


sealed class LoginState {

    object Initial : LoginState()

    object Loading : LoginState()

    object NoValidData : LoginState()

    object SuccessLogIn : LoginState()


}