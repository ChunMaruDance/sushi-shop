package com.chunmaru.sushishop.presentation.screens.home.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chunmaru.sushishop.R
import com.chunmaru.sushishop.ui.theme.Gray30

@Composable
fun SpecialCard(img: Int, discount: Int, dish: String) {

    Text(
        text = "Special for you",
        modifier = Modifier.padding(start = 16.dp, top = 26.dp, bottom = 12.dp),
        style = TextStyle(
            fontFamily = FontFamily.Serif,
            color = Gray30,
            fontSize = 22.sp,
        )
    )
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .shadow(
                elevation = 1.dp,
                ambientColor = Color(204, 204, 0),
                spotColor = Color(204, 204, 0),
                shape = RoundedCornerShape(20.dp)
            )
            .fillMaxWidth()
            .background(Color.Transparent),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(242, 242, 242),
                            Color(250, 250, 250)
                        )
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Image(
                painter = painterResource(id = img),
                contentDescription = "special dish image",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .offset(y = 10.dp)
            ) {
                Text(
                    text = "Only today ${discount}% OFF!",
                    style = TextStyle(
                        color = Gray30,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Serif
                    )
                )
                Text(
                    text = dish,

                    style = TextStyle(
                        color = Gray30,
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.W600,
                    )
                )
                androidx.compose.material.Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 10.sp,
                                baselineShift = BaselineShift.Superscript,
                            )
                        ) {
                            append("$")
                        }
                        append("8,30")
                    },
                    color = Gray30,
                    fontSize = 14.sp,
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.rightarrow),
                    contentDescription = "arrow",
                    modifier = Modifier
                        .size(20.dp)
                )
            }


        }

    }

}