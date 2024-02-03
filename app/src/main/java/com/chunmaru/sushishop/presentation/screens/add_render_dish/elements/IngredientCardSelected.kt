package com.chunmaru.sushishop.presentation.screens.add_render_dish.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.chunmaru.sushishop.data.convertImageByteArrayToBitmap
import com.chunmaru.sushishop.data.models.dishes.Ingredient
import com.chunmaru.sushishop.ui.theme.Gray120

@Composable

fun IngredientCardSelected(
    ingredientData: Ingredient,
    selected: Boolean,
    selectChanged: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .shadow(
                elevation = 2.dp,
                ambientColor = Color(204, 204, 0),
                spotColor = Color(204, 204, 0),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable {
                selectChanged()
            },
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) Color(247, 247, 247)
            else Gray120
        )
    ) {

        Row(
            modifier = Modifier.padding(end = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ingredientData.img.convertImageByteArrayToBitmap(),
                contentDescription = "ingredient image",
                Modifier
                    .padding(end = 4.dp)
                    .size(40.dp)

            )
            Text(
                text = ingredientData.name,
                fontSize = 12.sp,
                color = Gray120
            )

        }


    }

}