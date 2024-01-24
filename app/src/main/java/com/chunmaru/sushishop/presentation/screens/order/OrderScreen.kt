package com.chunmaru.sushishop.presentation.screens.order


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chunmaru.sushishop.R
import com.chunmaru.sushishop.presentation.screens.dish.DefaultTopBar

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun OrderScreen(
    navController: NavController,
    onOrderDetails: () -> Unit
) {

    val viewModel: OrderScreenViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.initController(navController)
        viewModel.getPreviewBackStackData()
    }

    when (val currentState = state.value) {
        OrderScreenState.Initial -> {}
        OrderScreenState.Pending -> {}
        is OrderScreenState.ShowData -> {

            val animVisible = remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { animVisible.value = true }

            AnimatedVisibility(
                modifier = Modifier.fillMaxSize(),
                visible = animVisible.value,
                enter = slideInVertically(
                    animationSpec = tween(500),
                ) { _ -> -40 } + expandVertically(expandFrom = Alignment.Top) + fadeIn(tween(500))
            ) {
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
                                    viewModel.putInCurrentBackStackToOrderDetails(
                                        onSuccess = { onOrderDetails() }
                                    )

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
                                    text = "Order Now",
                                    color = Color(247, 247, 247),
                                    fontSize = 21.sp
                                )

                            }


                        }
                    }
                ) { paddingValues ->


                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.onBackground)
                            .padding(bottom = 50.dp)
                    ) {

                        item {
                            DefaultTopBar(
                                title = "Order",
                                onMoreClick = { },
                                onBackClick = {

                                    viewModel.putInPreviewBackStackToHome(
                                        onSuccess = { navController.popBackStack() }
                                    )

                                }
                            )
                        }

                        items(
                            currentState.dishesWithCounters,
                            key = { it.dish.name }) { dishCounter ->

                            val dismissState = rememberDismissState()
                            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                                viewModel.removeDish(dishCounter)
                            }
                            SwipeToDismiss(
                                modifier = Modifier
                                    .padding(top = 10.dp, start = 15.dp, end = 15.dp)
                                    .animateItemPlacement(),
                                state = dismissState, background = {
                                    Card(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(RoundedCornerShape(15)),
                                        shape = RoundedCornerShape(15),
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color(255, 225, 225)
                                        )
                                    ) {
                                        Box(
                                            modifier = Modifier.fillMaxSize(),
                                            contentAlignment = Alignment.CenterEnd
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.delete),
                                                contentDescription = "delete image",
                                                tint = Color(255, 128, 128),
                                                modifier = Modifier
                                                    .size(35.dp)
                                                    .padding(end = 10.dp)

                                            )
                                        }


                                    }
                                },
                                directions = setOf(DismissDirection.EndToStart)
                            ) {
                                OrderCard(
                                    dishWithCounter = dishCounter,
                                    addCounter = {
                                        viewModel.addCounter(dishCounter)
                                    })

                            }


                        }


                    }


                }
            }


        }
    }


}
