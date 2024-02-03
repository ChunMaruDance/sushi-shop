package com.chunmaru.sushishop.presentation.screens.defaults

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ImageCardWithLauncher(
    img: ByteArray,
    modifier: Modifier,
    onImageSelected: (Uri) -> Unit,
) {

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) onImageSelected(uri)
        }

    DefaultImageCard(
        img = img,
        onClick = {
            launcher.launch("image/*")
        },
        modifier = modifier,
    )

}