package com.chunmaru.sushishop.presentation.screens.defaults.alerts

interface AlertSaveHandler<T> {

    val data: T

    fun onSuccess()

    fun onError()

}