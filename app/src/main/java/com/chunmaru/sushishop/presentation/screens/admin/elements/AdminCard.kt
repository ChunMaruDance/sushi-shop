package com.chunmaru.sushishop.presentation.screens.admin.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdminCard(
    tittle: String,
    img: Int,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .padding(top = 18.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 1.dp,
                ambientColor = Color(204, 204, 0),
                spotColor = Color(204, 204, 0),
                shape = RoundedCornerShape(30)
            )
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color(250, 250, 250)
        ),
        shape = RoundedCornerShape(30)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
        ) {

            Icon(
                painter = painterResource(id = img),
                contentDescription = "menu icon image",
                modifier = Modifier.size(35.dp),
                tint = Color.Black
            )
            Text(
                text = tittle,
                modifier = Modifier.padding(start = 12.dp),
                style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    color = Color.Black,
                    fontSize = 21.sp,
                )
            )

        }


    }

}