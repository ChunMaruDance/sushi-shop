package com.chunmaru.sushishop.presentation.screens.defaults

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultProgressBar() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        CircularProgressIndicator(
            color = Color.Black,
            strokeWidth = 2.dp,
            modifier = Modifier.size(70.dp),
            trackColor = Color.White
        )


    }

}