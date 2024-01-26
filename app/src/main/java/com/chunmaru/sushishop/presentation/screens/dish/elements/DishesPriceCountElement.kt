package com.chunmaru.sushishop.presentation.screens.dish.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Icon
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
import com.chunmaru.sushishop.ui.theme.Gray120
import com.chunmaru.sushishop.ui.theme.Gray30

@Composable
fun DishesPriceCountElement(
    count: Int,
    price: Float,
    onAddClick: () -> Unit,
    onRemoveClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .padding(top = 6.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 24.sp,
                        baselineShift = BaselineShift.Superscript,
                        fontWeight = FontWeight.Medium
                    )
                ) {
                    append("$")
                }
                append(price.toString())
            },
            color = Gray30,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold

        )

        ButtonsAddRemove(
            count = count,
            onAddClick = onAddClick,
            onRemoveClick = onRemoveClick
        )

    }

}

@Composable
fun ButtonsAddRemove(
    count: Int,
    onAddClick: () -> Unit,
    onRemoveClick: () -> Unit
) {

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(40))
                .background(Color(247, 247, 247)),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = { onRemoveClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.minus),
                    contentDescription = "remove icon",
                    Modifier.size(20.dp),
                    tint = if (count == 1) Gray120 else Gray30
                )
            }

            Text(
                text = count.toString(),
                color = Gray30,
                fontSize = 23.sp
            )

            IconButton(onClick = { onAddClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.plus2),
                    contentDescription = "add icon",
                    Modifier.size(20.dp),
                    tint = Gray30
                )
            }


        }


}
