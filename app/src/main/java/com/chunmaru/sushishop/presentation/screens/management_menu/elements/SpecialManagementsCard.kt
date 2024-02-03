package com.chunmaru.sushishop.presentation.screens.management_menu.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.chunmaru.sushishop.data.convertImageByteArrayToBitmap
import com.chunmaru.sushishop.data.models.dishes.Dish

@Composable
fun SpecialManagementCard(
    dish: Dish,
    modifier: Modifier
) {

    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(247, 247, 247)
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp),
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
                AsyncImage(
                    model = dish.image.convertImageByteArrayToBitmap(),
                    contentDescription = "dish image",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(5.dp)
                )
            }


            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = dish.name,
                    fontSize = 12.sp,
                    style = TextStyle(
                        fontFamily = FontFamily.Serif
                    ),
                    color = Color(204, 204, 0),
                    fontWeight = FontWeight.W600
                )

            }

        }


    }


}