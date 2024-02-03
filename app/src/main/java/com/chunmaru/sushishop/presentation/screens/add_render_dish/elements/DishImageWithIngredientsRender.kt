package com.chunmaru.sushishop.presentation.screens.add_render_dish.elements

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.chunmaru.sushishop.data.convertImageByteArrayToBitmap
import com.chunmaru.sushishop.data.models.dishes.DishWithIngredientsCategories
import com.chunmaru.sushishop.data.models.dishes.Ingredient
import com.chunmaru.sushishop.presentation.screens.management_menu.elements.SpecialAddCard


@Composable
fun DishImageWithIngredientsRender(
    dishWithIngredients: DishWithIngredientsCategories,
    onIngredientClick: (Ingredient) -> Unit,
    onLoadMoreIngredients: () -> Unit,
    onImageSelected: (Uri) -> Unit,
    onCategoryChange: (String) -> Unit,
    onTittleChange: (String) -> Unit
) {

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) onImageSelected(uri)
        }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        if (dishWithIngredients.dish.image.isEmpty()) {
            SpecialAddCard(
                height = 220.dp,
                onClick = { launcher.launch("image/*") }
            )
        } else {
            AsyncImage(
                model = dishWithIngredients.dish.image.convertImageByteArrayToBitmap(),
                contentDescription = "dish img",
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(220.dp)
                    .clip(RoundedCornerShape(12))
                    .clickable { launcher.launch("image/*") },
                contentScale = ContentScale.FillBounds
            )
        }
        DishTextInfoChange(
            title = dishWithIngredients.dish.name,
            selectedCategory = dishWithIngredients.dish.category,
            categories = dishWithIngredients.categories,
            onCategoryChange = onCategoryChange,
            onTittleChange = onTittleChange
        )

        LazyRow(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
        ) {

            dishWithIngredients.ingredients.forEach { ingredientSelect ->
                item(key = ingredientSelect.ingredient.id) {
                    IngredientCardSelected(
                        ingredientData = ingredientSelect.ingredient,
                        selected = ingredientSelect.select,
                        selectChanged = { onIngredientClick(ingredientSelect.ingredient) }
                    )
                }
            }


        }

    }


}