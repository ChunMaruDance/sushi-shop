package com.chunmaru.sushishop.presentation.screens.management_menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.chunmaru.sushishop.presentation.screens.defaults.DefaultProgressBar
import com.chunmaru.sushishop.presentation.screens.defaults.DefaultTopBar
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState

@Composable
fun ManagementMenuScreen(
    onBackClick: () -> Unit
) {

    val viewModel: ManagementScreenViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()

    when (state.value) {
        is ScreenState.Initial -> {}
        is ScreenState.Pending -> {
            DefaultProgressBar()
        }

        is ScreenState.Success -> {

            ManagementScreenContent(
                onBackClick = onBackClick,
            )
        }
    }


}

@Composable
private fun ManagementScreenContent(
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground)
    ) {
        DefaultTopBar(
            title = "Management", onMoreClick = { },
            onBackClick = onBackClick
        )


    }
}