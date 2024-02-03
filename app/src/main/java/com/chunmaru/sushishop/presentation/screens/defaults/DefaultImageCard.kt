package com.chunmaru.sushishop.presentation.screens.defaults

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.chunmaru.sushishop.data.convertImageByteArrayToBitmap
import com.chunmaru.sushishop.presentation.screens.management_menu.elements.SpecialAddCard

@Composable
fun DefaultImageCard(
    img: ByteArray, onClick: () -> Unit,
    modifier: Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (img.isEmpty()) {
            SpecialAddCard(
                modifier = modifier,
                onClick = { onClick() }
            )
        } else {
            AsyncImage(
                model = img.convertImageByteArrayToBitmap(),
                contentDescription = "dish img",
                modifier = modifier.clickable { onClick() },
                contentScale = ContentScale.FillBounds
            )
        }


    }


}