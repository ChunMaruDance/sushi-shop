package com.chunmaru.sushishop.presentation.screens.defaults

sealed class ScreenState<T> {

    class Initial<T> : ScreenState<T>()

    class Pending<T> : ScreenState<T>()

    data class Success<T>(
        val data: T
    ) : ScreenState<T>()


}