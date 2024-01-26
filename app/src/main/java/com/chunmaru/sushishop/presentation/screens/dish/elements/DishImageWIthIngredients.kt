package com.chunmaru.sushishop.presentation.screens.dish.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chunmaru.sushishop.R
import com.chunmaru.sushishop.data.models.dishes.IngredientsData
import com.chunmaru.sushishop.ui.theme.Gray120

@Composable
fun DishImageWithIngredients(
    image: Int,
    ingredients: List<IngredientsData>
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10))
                    .background(Color(247, 247, 247))
                    .clickable {

                    },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.up),
                    contentDescription = "arrow up image",
                    tint = Gray120,
                    modifier = Modifier
                        .padding(7.dp)
                        .size(25.dp)
                )

            }

            LazyColumn(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .height(280.dp)
            ) {

                items(ingredients) { ingredient ->
                    IngredientCard(ingredientData = ingredient)
                }

            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10))
                    .background(Color(247, 247, 247))
                    .clickable {

                    },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.down),
                    contentDescription = "arrow up image",
                    tint = Gray120,
                    modifier = Modifier
                        .padding(7.dp)
                        .size(25.dp)
                )

            }


        }//


        Image(
            painter = painterResource(id = image),
            contentDescription = "dish img",
            Modifier.size(220.dp)
        )


    }

}

@Composable
fun IngredientCard(
    ingredientData: IngredientsData
) {

    Box(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .clip(RoundedCornerShape(10))
            .background(Color(247, 247, 247))
            .clickable {

            },
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = ingredientData.img),
                contentDescription = "ingredient image",
                Modifier.size(45.dp)
            )
            Text(
                modifier = Modifier.offset(y = (-5).dp),
                text = ingredientData.name,
                fontSize = 12.sp,
                color = Gray120
            )

        }


    }
}
