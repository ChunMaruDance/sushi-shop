package com.chunmaru.sushishop.presentation.screens.defaults.ui_elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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