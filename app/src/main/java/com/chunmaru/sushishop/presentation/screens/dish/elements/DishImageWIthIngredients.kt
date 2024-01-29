package com.chunmaru.sushishop.presentation.screens.dish.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.chunmaru.sushishop.data.models.dishes.IngredientsData
import com.chunmaru.sushishop.presentation.screens.dish.DishTextInfo
import com.chunmaru.sushishop.ui.theme.Gray120

@Composable
fun DishImageWithIngredients(
    image: Int,
    ingredients: List<IngredientsData>,
    dishName: String,
    dishCategory: String
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Image(
            painter = painterResource(id = image),
            contentDescription = "dish img",
            Modifier
                .align(Alignment.CenterHorizontally)
                .size(220.dp)
        )

        DishTextInfo(
            title = dishName,
            category = dishCategory
        )


        LazyRow(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
        ) {

            items(ingredients) { ingredient ->
                IngredientCard(ingredientData = ingredient)
            }

        }

    }//


}

@Composable
fun IngredientCard(
    ingredientData: IngredientsData
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

            },
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(247, 247, 247)
        )
    ) {

        Row(
            modifier = Modifier.padding(end = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = ingredientData.img),
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
