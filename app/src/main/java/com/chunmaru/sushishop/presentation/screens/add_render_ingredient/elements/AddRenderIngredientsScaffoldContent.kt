package com.chunmaru.sushishop.presentation.screens.add_render_ingredient.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chunmaru.sushishop.data.models.dishes.Ingredient
import com.chunmaru.sushishop.data.readBytesFromUri
import com.chunmaru.sushishop.presentation.screens.defaults.CustomMultilineHintTextField
import com.chunmaru.sushishop.presentation.screens.defaults.ImageCardWithLauncher
import com.chunmaru.sushishop.presentation.screens.login.elements.DefaultButton
import com.chunmaru.sushishop.ui.theme.Gray30


@Composable
fun AddRenderIngredientsScaffoldContent(
    paddingValues: PaddingValues,
    ingredients: List<Ingredient>,
    onSaveIngredient: (Ingredient) -> Unit
) {

    val image = remember {
        mutableStateOf(byteArrayOf())
    }

    val name = remember {
        mutableStateOf("")
    }

    val descriptions = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            ImageCardWithLauncher(
                img = image.value,
                onImageSelected = {
                    image.value = it.readBytesFromUri(context)
                },
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .size(230.dp)
                    .shadow(
                        elevation = 3.dp,
                        ambientColor = Color.Gray,
                        spotColor = Color.Gray,
                        shape = RoundedCornerShape(15.dp)
                    )
            )
        }

        item {
            CustomMultilineHintTextField(
                value = name.value,
                onValueChanged = {
                    name.value = it
                },
                oneLine = true,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 19.sp,
                    fontFamily = FontFamily.Serif
                ),
                modifier = Modifier
                    .shadow(
                        elevation = 2.dp,
                        ambientColor = Color(204, 204, 0),
                        spotColor = Color(204, 204, 0),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .background(Color(247, 247, 247))
                    .padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp),
                hint = "Ingredient Name"
            )
        }

        item {
            CustomMultilineHintTextField(
                value = descriptions.value,
                onValueChanged = {
                    descriptions.value = it
                },
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 19.sp,
                    fontFamily = FontFamily.Serif
                ),
                modifier = Modifier
                    .width(350.dp)
                    .shadow(
                        elevation = 2.dp,
                        ambientColor = Color(204, 204, 0),
                        spotColor = Color(204, 204, 0),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .background(Color(247, 247, 247))
                    .padding(10.dp),
                hint = "Description",
                minLines = 5,
                maxLines = 8
            )
        }

        item {
            DefaultButton(modifier = Modifier
                .height(80.dp)
                .width(350.dp)
                .padding(vertical = 12.dp)
                .shadow(
                    elevation = 2.dp,
                    ambientColor = Color(204, 204, 0),
                    spotColor = Color(204, 204, 0),
                    shape = RoundedCornerShape(15.dp)
                ),
                title = "Create Ingredient",
                textColor = Gray30,
                fontSize = 23,
                background = Color(247, 247, 247),
                onClick = {
                    onSaveIngredient(
                        Ingredient(
                            name = name.value,
                            descriptions = descriptions.value,
                            id = 0,
                            img = image.value
                        )
                    )
                })
        }


    }
}