package com.chunmaru.sushishop.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.chunmaru.sushishop.R
import com.chunmaru.sushishop.presentation.screens.home.elements.HomeScreenCarousel
import com.chunmaru.sushishop.presentation.screens.home.elements.HomeTabBar
import com.chunmaru.sushishop.presentation.screens.home.elements.HomeTopBar
import com.chunmaru.sushishop.presentation.screens.home.elements.SpecialCard
import com.chunmaru.sushishop.ui.theme.Gray30

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    onDishNavigate: () -> Unit,
    onOrderNavigate: () -> Unit,
    onLoginScreen: () -> Unit
) {

    val viewModel: HomeScreenViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()

    val openBottomSheet = rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        viewModel.initController(navController)
    }

    when (val currentState = state.value) {
        HomeScreenState.Initial -> {
            viewModel.initData()
        }

        is HomeScreenState.PendingData -> {

            val composition = rememberLottieComposition(
                spec = LottieCompositionSpec.Asset("start_load_anim.json")
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LottieAnimation(
                    modifier = Modifier.fillMaxSize(),
                    isPlaying = true,
                    composition = composition.value,
                    iterations = LottieConstants.IterateForever,
                    contentScale = ContentScale.Crop
                )

            }

        }

        is HomeScreenState.ShowData -> {

            val animVisible = remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { animVisible.value = true }

            AnimatedVisibility(
                modifier = Modifier.fillMaxSize(),
                visible = animVisible.value,
                enter = slideInHorizontally(
                    animationSpec = tween(500),
                ) { fullWidth -> -fullWidth / 3 } + fadeIn(tween(500))
            ) {

                val ordersCounters = viewModel.ordersCount.collectAsState()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.onBackground)
                ) {
                    HomeTopBar(
                        badgeCounter = ordersCounters.value,
                        onSearchClick = { },
                        onMoreClick = { openBottomSheet.value = true },
                        onBasketClick = {

                            viewModel.putOrderInCurrentBackStack(
                                onSuccess = { onOrderNavigate() }
                            )
                        }
                    )

                    Text(
                        modifier = Modifier.padding(start = 16.dp, top = 12.dp, bottom = 12.dp),
                        text = "What would you\nlike to order?",
                        color = Gray30,
                        fontSize = 26.sp,
                        style = TextStyle(lineHeight = 32.sp),
                    )

                    when (val categoryState = currentState.categoryState) {
                        CategoryState.Initial -> {}
                        is CategoryState.PendingCategory -> {
                            HomeTabBar(
                                types = TapBarItems.DEFAULT,
                                selected = categoryState.activeCategory,
                                onItemClick = { viewModel.changeCategory(it) }
                            )

                            SpecialCard(img = R.drawable.test2, discount = 12, dish = "dishi2")
                        }

                        is CategoryState.ShowCategory -> {
                            HomeTabBar(
                                types = TapBarItems.DEFAULT,
                                selected = categoryState.activeCategory,
                                onItemClick = { viewModel.changeCategory(it) }
                            )
                            HomeScreenCarousel(
                                dishes = categoryState.dishes,
                                addToBasket = { viewModel.addToOrder(dish = it) },
                                onItemClick = { dish ->
                                    viewModel.putDishInCurrentBackStack(dish,
                                        onSuccess = {
                                            onDishNavigate()
                                        })
                                }
                            )
                            SpecialCard(img = R.drawable.test2, discount = 12, dish = "dishi")
                        }
                    }
                }
            }

            if (openBottomSheet.value) {

                ModalBottomSheet(
                    onDismissRequest = { openBottomSheet.value = false },
                    containerColor = MaterialTheme.colorScheme.onBackground
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 50.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            onLoginScreen()
                            openBottomSheet.value = false
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.admin_panel),
                                contentDescription = "build icon",
                                tint = Gray30,
                                modifier = Modifier.size(35.dp)
                            )
                        }
                        Text(
                            text = "Admin panel",
                            fontSize = 21.sp,
                            color = Gray30
                        )
                    }


                }
            }


        }

    }

}



