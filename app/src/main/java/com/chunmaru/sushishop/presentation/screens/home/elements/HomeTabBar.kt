package com.chunmaru.sushishop.presentation.screens.home.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chunmaru.sushishop.ui.theme.Gray30
import com.chunmaru.sushishop.ui.theme.Gray120

@Composable
fun HomeTabBar(
    types: List<String>,
    selected: String,
    onItemClick: (String) -> Unit
) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        items(types) { item ->
            TabBarItemCard(
                title = item,
                isActive = item == selected,
                onItemClick = onItemClick
            )
        }
    }

}

@Composable
fun TabBarItemCard(
    title: String,
    isActive: Boolean,
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(45.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            color = if (isActive) Gray30 else Gray120,
            modifier = Modifier.clickable {
                onItemClick(title)
            }
        )
        if (isActive) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .size(8.dp)
                    .background(Gray30)
            )


        }


    }

}