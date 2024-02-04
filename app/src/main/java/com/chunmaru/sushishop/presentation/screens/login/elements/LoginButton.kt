package com.chunmaru.sushishop.presentation.screens.login.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DefaultButton(
    modifier: Modifier,
    title: String,
    textColor: Color = Color(247, 247, 247),
    fontSize: Int = 21,
    background: Color = Color.Black,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = background
        )
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = title,
                style = TextStyle(
                    color = textColor,
                    fontSize = fontSize.sp,
                    fontFamily = FontFamily.Serif
                )
            )

        }


    }


}