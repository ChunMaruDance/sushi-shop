package com.chunmaru.sushishop.presentation.screens.order.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chunmaru.sushishop.R
import com.chunmaru.sushishop.data.models.dishes.DishWithCounter
import com.chunmaru.sushishop.ui.theme.Gray120
import com.chunmaru.sushishop.ui.theme.Gray30

@Composable
fun OrderCard(
    dishWithCounter: DishWithCounter,
    addCounter: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15)),
        shape = RoundedCornerShape(15),
        colors = CardDefaults.cardColors(
            containerColor = Color(247, 247, 247)
        )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(15)),
                shape = RoundedCornerShape(15),
                colors = CardDefaults.cardColors(
                    containerColor = Color(230, 230, 230)
                )
            ) {
                Image(
                    painter = painterResource(id = dishWithCounter.dish.image),
                    contentDescription = "dish image",
                    modifier = Modifier
                        .size(90.dp)
                        .padding(5.dp)
                )
            }


            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
            ) {

                Text(
                    text = dishWithCounter.dish.category,
                    color = Gray120,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W600
                )
                Text(
                    text = dishWithCounter.dish.name,
                    color = Gray30,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600
                )
                androidx.compose.material.Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 12.sp,
                                baselineShift = BaselineShift.Superscript,
                            )
                        ) {
                            append("$")
                        }
                        append(dishWithCounter.dish.price.toString())
                    },
                    color = Gray30,
                    fontSize = 18.sp,
                )
            }


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(7.dp),
                modifier = Modifier.padding(end = 5.dp, start = 5.dp)
            ) {
                Text(
                    text = dishWithCounter.counter.toString(),
                    color = Gray30,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600
                )
                IconButton(onClick = { addCounter() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = "add to Basket",
                        modifier = Modifier.size(35.dp),
                        tint = Gray30
                    )
                }


            }


        }


    }


}