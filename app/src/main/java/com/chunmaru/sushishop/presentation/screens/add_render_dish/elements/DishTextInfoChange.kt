package com.chunmaru.sushishop.presentation.screens.add_render_dish.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chunmaru.sushishop.data.models.Category
import com.chunmaru.sushishop.presentation.screens.defaults.CustomMultilineHintTextField
import com.chunmaru.sushishop.ui.theme.Gray30

@Composable
fun DishTextInfoChange(
    title: String,
    selectedCategory: String,
    categories: List<Category>,
    onTittleChange: (String) -> Unit,
    onCategoryChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowDown
    else
        Icons.Filled.KeyboardArrowUp

    Card(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 6.dp)
            .shadow(
                elevation = 2.dp,
                ambientColor = Color(204, 204, 0),
                spotColor = Color(204, 204, 0),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable {
                expanded = !expanded
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(247, 247, 247)
        ),
        shape = RoundedCornerShape(15.dp)

    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedCategory.ifEmpty { "Select Category" },
                color = Color.Gray,
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(start = 6.dp)
            )
            IconButton(onClick = { }) {
                Icon(
                    imageVector = icon, contentDescription = "icon",
                    tint = Color.Gray
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
            ) {

                categories.forEach { label ->
                    DropdownMenuItem(onClick = {
                        onCategoryChange(label.name)
                    }) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 7.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = label.name,
                                color = Gray30,
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

        }


    }

    CustomMultilineHintTextField(
        value = title,
        onValueChanged = {
            onTittleChange(it)
        },
        oneLine = true,
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp, fontFamily = FontFamily.Serif),
        modifier = Modifier
            .shadow(
                elevation = 2.dp,
                ambientColor = Color(204, 204, 0),
                spotColor = Color(204, 204, 0),
                shape = RoundedCornerShape(15.dp)
            )
            .background(Color(247, 247, 247))
            .padding(10.dp),
        hint = "Dish Name"
    )

}