package com.chunmaru.sushishop.presentation.screens.order_details.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomButton(
    title: String,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp, end = 14.dp, bottom = 7.dp)
                .clip(RoundedCornerShape(20))
                .background(color = Color.Transparent)
                .height(60.dp)
                .clickable {
                    onClick()



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
                    text = title,
                    color = Color(247, 247, 247),
                    fontSize = 21.sp
                )

            }


        }
    }

}