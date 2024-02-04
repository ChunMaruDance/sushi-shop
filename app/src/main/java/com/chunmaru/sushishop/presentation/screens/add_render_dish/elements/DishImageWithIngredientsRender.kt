package com.chunmaru.sushishop.presentation.screens.add_render_dish.elements

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chunmaru.sushishop.data.models.dishes.DishWithIngredientsCategories
import com.chunmaru.sushishop.data.models.dishes.Ingredient
import com.chunmaru.sushishop.presentation.screens.defaults.ImageCardWithLauncher


@Composable
fun DishImageWithIngredientsRender(
    dishWithIngredients: DishWithIngredientsCategories,
    onIngredientClick: (Ingredient) -> Unit,
    onLoadMoreIngredients: () -> Unit,
    onImageSelected: (Uri) -> Unit,
    onCategoryChange: (String) -> Unit,
    onTittleChange: (String) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ImageCardWithLauncher(
            img = dishWithIngredients.dish.image,
            modifier = Modifier
                .size(250.dp)
                .shadow(
                    elevation = 2.dp,
                    ambientColor = Color.Gray,
                    spotColor = Color.Gray,
                    shape = RoundedCornerShape(15.dp)
                ), onImageSelected = onImageSelected
        )

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