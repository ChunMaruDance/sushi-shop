package com.chunmaru.sushishop.presentation.screens.defaults.alerts


sealed class DefaultAlertsState<T> {

    class Initial<T> : DefaultAlertsState<T>()

    class Loading<T> : DefaultAlertsState<T>()

    data class Confirm<T>(
        val data: T
    ) : DefaultAlertsState<T>()

    class Success<T> : DefaultAlertsState<T>()

    class Error<T> : DefaultAlertsState<T>()

}