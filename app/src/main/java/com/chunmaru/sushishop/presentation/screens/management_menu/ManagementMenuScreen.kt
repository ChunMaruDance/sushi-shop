package com.chunmaru.sushishop.presentation.screens.management_menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chunmaru.sushishop.R
import com.chunmaru.sushishop.presentation.screens.defaults.DefaultProgressBar
import com.chunmaru.sushishop.presentation.screens.defaults.DefaultTopBar
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState
import com.chunmaru.sushishop.presentation.screens.home.elements.SpecialCard
import com.chunmaru.sushishop.presentation.screens.login.elements.DefaultButton
import com.chunmaru.sushishop.presentation.screens.management_menu.elements.SpecialAddCard
import com.chunmaru.sushishop.presentation.screens.management_menu.elements.SpecialManagementCard
import com.chunmaru.sushishop.ui.theme.Gray30

@Composable
fun ManagementMenuScreen(
    onBackClick: () -> Unit,
    onAddRenderDish: () -> Unit,
    onAddIngredientNavigate: () -> Unit
) {

    val viewModel: ManagementScreenViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()

    when (val currentState = state.value) {
        is ScreenState.Initial -> {}
        is ScreenState.Pending -> {
            DefaultProgressBar()
        }

        is ScreenState.Success -> {

            ManagementScreenContent(
                data = currentState.data,
                onBackClick = onBackClick,
                onAddDishNavigate = onAddRenderDish,
                onAddIngredientNavigate = onAddIngredientNavigate
            )
        }
    }

}

@Composable
private fun ManagementScreenContent(
    data: ManagementData,
    onBackClick: () -> Unit,
    onAddDishNavigate: () -> Unit,
    onAddIngredientNavigate: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            DefaultTopBar(
                title = "Management", onMoreClick = { },
                onBackClick = onBackClick
            )
        }


        item {
            Text(
                text = "Special",
                modifier = Modifier.padding(top = 21.dp, bottom = 12.dp),
                style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    color = Gray30,
                    fontSize = 23.sp
                )
            )
        }

        item {
            if (data.specialDish != null) {
                SpecialManagementCard(dish = data.specialDish)
            } else {
                SpecialAddCard(onClick = {})
            }
        }

        item {
            Text(
                text = "Ingredients",
                modifier = Modifier.padding(top = 21.dp, bottom = 12.dp),
                style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    color = Gray30,
                    fontSize = 23.sp
                )
            )
        }
        item {

            DefaultButton(modifier = Modifier
                .height(70.dp)
                .padding(horizontal = 12.dp, vertical = 3.dp)
                .clip(RoundedCornerShape(20)), title = "Add Dish", onClick = {
                onAddDishNavigate()
            })

            DefaultButton(modifier = Modifier
                .height(70.dp)
                .padding(horizontal = 12.dp, vertical = 3.dp)
                .clip(RoundedCornerShape(20)), title = "Add Ingredient", onClick = {
                onAddIngredientNavigate()
            })


        }


    }
}