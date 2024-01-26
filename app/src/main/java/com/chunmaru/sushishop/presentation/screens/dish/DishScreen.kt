package com.chunmaru.sushishop.presentation.screens.dish

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chunmaru.sushishop.data.models.dishes.DishWithCounter
import com.chunmaru.sushishop.data.models.dishes.TestDish
import com.chunmaru.sushishop.presentation.navigation.NavigationEntryKey
import com.chunmaru.sushishop.presentation.screens.ScreenState
import com.chunmaru.sushishop.ui.theme.Gray120
import com.chunmaru.sushishop.ui.theme.Gray30

@Composable
fun DishScreen(
    navController: NavController,
    onBuyClick: () -> Unit
) {

    val viewModel: DishScreenViewModel = hiltViewModel()
    viewModel.initController(navController)
    val state = viewModel.state.collectAsState()



    when (val currentState = state.value) {
        is ScreenState.Initial -> {
            viewModel.getPreviewBackStackData()
        }

        is ScreenState.Pending -> {}
        is ScreenState.Success -> {


            val dish = currentState.data.dishWithCounter.dish
            val ingredients = currentState.data.ingredientsData
            val counter = currentState.data.dishWithCounter.counter

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 14.dp, end = 14.dp, bottom = 7.dp)
                            .clip(RoundedCornerShape(20))
                            .background(color = Color.Transparent)
                            .height(60.dp)
                            .clickable {
                                viewModel.putInCurrentBackStack(
                                    onSuccess = { onBuyClick() })
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Black
                        ),
                        elevation = CardDefaults.elevatedCardElevation(
                            defaultElevation = 2.dp
                        )
                    ) {

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "Buy Now",
                                color = Color(247, 247, 247),
                                fontSize = 21.sp
                            )

                        }


                    }

                },
                containerColor = MaterialTheme.colorScheme.onBackground
            ) { paddingValues ->

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp)
                ) {


                    item {
                        DefaultTopBar(
                            title = "Detail",
                            onBackClick = { navController.popBackStack() },
                            onMoreClick = {
                            },
                        )
                    }

                    item {
                        DishTextInfo(
                            title = dish.name,
                            category = dish.category
                        )
                    }

                    item {
                        DishImageWithIngredients(
                            image = dish.image,
                            ingredients = ingredients
                        )
                    }


                    item {

                        DishesPriceCountElement(
                            count = counter,
                            dish.price,
                            onAddClick = {
                               viewModel.addCounter()
                            },
                            onRemoveClick = {
                                if (counter > 1) viewModel.removeCounter()
                            }
                        )
                    }

                    item {
                        Text(
                            text = "Description",
                            color = Gray30,
                            fontWeight = FontWeight.Bold,
                            fontSize = 23.sp,
                            modifier = Modifier.padding(top = 15.dp, bottom = 5.dp)
                        )
                        Text(
                            text = dish.descriptions,
                            color = Gray120,
                            modifier = Modifier.padding(bottom = 50.dp),
                            fontSize = 14.sp
                        )
                    }


                }


            }
        }
    }


}

@Composable
fun DishTextInfo(
    title: String,
    category: String
) {
    Text(
        text = category,
        fontSize = 21.sp,
        modifier = Modifier
            .padding(top = 21.dp),
        color = Color(204, 204, 0)
    )

    Text(
        text = title,
        color = Gray30,
        fontSize = 32.sp,
        maxLines = 2,
        fontWeight = FontWeight.Bold,
        overflow = TextOverflow.Ellipsis
    )
}
