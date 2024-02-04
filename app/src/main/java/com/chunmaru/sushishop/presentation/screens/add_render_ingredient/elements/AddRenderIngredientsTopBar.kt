package com.chunmaru.sushishop.presentation.screens.add_render_ingredient.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chunmaru.sushishop.R
import com.chunmaru.sushishop.presentation.screens.defaults.ui_elements.DefaultTopBarItem
import com.chunmaru.sushishop.ui.theme.Gray30

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRenderIngredientsTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onBackClick: () -> Unit,
    tittle: String
) {

    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = tittle,
                    color = Gray30,
                    fontSize = 26.sp
                )
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onBackground,
            scrolledContainerColor = Color(200, 200, 200),
            titleContentColor = MaterialTheme.colorScheme.background,
        ),
        navigationIcon = {
            DefaultTopBarItem(
                image = R.drawable.back, onClick = { onBackClick() },
                paddingValues = PaddingValues(start = 8.dp)
            )
        },
        actions = {
            DefaultTopBarItem(
                image = R.drawable.sort, onClick = {},
                paddingValues = PaddingValues(end = 8.dp)
            )
        }
    )

}