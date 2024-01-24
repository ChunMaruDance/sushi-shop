package com.chunmaru.sushishop.presentation.screens.dish

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chunmaru.sushishop.R
import com.chunmaru.sushishop.presentation.screens.home.elements.TopBarCard
import com.chunmaru.sushishop.ui.theme.Gray30

@Composable
fun DishTopBar(
    onBackClick: () -> Unit,
    onMoreClick: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        DefaultTopBar(
            title = "Detail",
            onMoreClick = onMoreClick,
            onBackClick = onBackClick
        )


    }

}

@Composable
fun DefaultTopBar(
    title: String,
    onMoreClick: () -> Unit,
    onBackClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        DefaultTopBarItem(image = R.drawable.back, onClick = onBackClick)
        Text(
            text = title,
            color = Gray30,
            fontSize = 26.sp
        )
        DefaultTopBarItem(image = R.drawable.sort, onClick = onMoreClick)


    }

}

@Composable
fun DefaultTopBarItem(
    image: Int,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            //     .padding(end = 12.dp, start = 12.dp)
            .size(42.dp)
            .clip(CircleShape)
            .background(Color(247, 247, 247)),
        contentAlignment = Alignment.Center,
    ) {
        IconButton(onClick = { onClick() }) {
            Icon(
                painter = painterResource(id = image),
                contentDescription = "menu item",
                tint = Gray30,
                modifier = Modifier
                    .size(25.dp)
            )
        }


    }

}

