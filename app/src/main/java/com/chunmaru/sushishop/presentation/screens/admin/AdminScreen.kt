package com.chunmaru.sushishop.presentation.screens.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.chunmaru.sushishop.R
import com.chunmaru.sushishop.data.models.admin.Admin
import com.chunmaru.sushishop.presentation.screens.admin.elements.AdminCard
import com.chunmaru.sushishop.presentation.screens.defaults.DefaultProgressBar
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState
import com.chunmaru.sushishop.ui.theme.Gray120
import com.chunmaru.sushishop.ui.theme.Gray30

@Composable
fun AdminScreen(
    onManagementMenuNavigate: () -> Unit,
    onBackNavigate: () -> Unit,
    onChangeProfileNavigate: () -> Unit,
    onOrdersHistoryNavigate: () -> Unit,
    onActiveOrdersNavigate: () -> Unit,
    onStatisticsNavigate: () -> Unit
) {

    val viewModel: AdminViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()

    when (val currentState = state.value) {
        is ScreenState.Initial -> {}
        is ScreenState.Pending -> {
            DefaultProgressBar()
        }

        is ScreenState.Success -> {
            AdminScreenContent(
                state = currentState,
                onChangeProfileClick = onChangeProfileNavigate,
                onActiveOrdersClick = onActiveOrdersNavigate,
                onOrdersHistoryClick = onOrdersHistoryNavigate,
                onStatisticsClick = onStatisticsNavigate,
                onManagementMenuDishClick = onManagementMenuNavigate,
                onLogoutClick = {
                    viewModel.logout(onBackNavigate)
                }
            )
        }
    }

}


@Composable
private fun AdminScreenContent(
    state: ScreenState.Success<Admin>,
    onChangeProfileClick: () -> Unit,
    onActiveOrdersClick: () -> Unit,
    onOrdersHistoryClick: () -> Unit,
    onStatisticsClick: () -> Unit,
    onManagementMenuDishClick: () -> Unit,
    onLogoutClick: () -> Unit
) {

    val profile = state.data

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onBackground)
            .fillMaxSize()
            .padding(top = 15.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            text = "PROFILE",
            style = TextStyle(
                fontFamily = FontFamily.Serif,
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier.padding(bottom = 26.dp, top = 6.dp)
        )


        Box(
            modifier = Modifier
                .padding(bottom = 21.dp)
                .fillMaxWidth()
                .height(230.dp)

        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 3.dp,
                        ambientColor = Color(204, 204, 0),
                        spotColor = Color(204, 204, 0),
                        shape = RoundedCornerShape(15)
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = Color(250, 250, 250),
                ),
                shape = RoundedCornerShape(15),
            ) {


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    val composition = rememberLottieComposition(
                        spec = LottieCompositionSpec.Asset(assetName = "admin_anim.json")
                    )
                    LottieAnimation(
                        modifier = Modifier
                            .padding(top = 12.dp, bottom = 12.dp)
                            .size(140.dp)
                            .clip(RoundedCornerShape(50)),
                        isPlaying = true,
                        composition = composition.value,
                        iterations = LottieConstants.IterateForever,
                        contentScale = ContentScale.FillBounds,
                    )


                    Text(
                        text = profile.username,
                        color = Gray30,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Text(
                        text = profile.email,
                        color = Gray120,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 21.dp)
                    )

                }

            }

            IconButton(
                onClick = { onChangeProfileClick() },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 6.dp, end = 6.dp)

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.edit_button),
                    contentDescription = "edit",
                    tint = Gray120,
                    modifier = Modifier.size(25.dp)
                )
            }


        }

        AdminCard(
            tittle = "Active Orders",
            img = R.drawable.order,
            onClick = onActiveOrdersClick
        )
        AdminCard(
            tittle = "Order History",
            img = R.drawable.history,
            onClick = onOrdersHistoryClick
        )
        AdminCard(
            tittle = "Statistics",
            img = R.drawable.statistic,
            onClick = onStatisticsClick
        )
        AdminCard(
            tittle = "Management Menu", img = R.drawable.menu_orders,
            onClick = onManagementMenuDishClick
        )
        AdminCard(
            tittle = "Logout",
            img = R.drawable.logout,
            onClick = onLogoutClick
        )

    }


}
