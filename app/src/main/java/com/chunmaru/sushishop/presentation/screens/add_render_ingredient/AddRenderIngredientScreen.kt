package com.chunmaru.sushishop.presentation.screens.add_render_ingredient


import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chunmaru.sushishop.data.readBytesFromUri
import com.chunmaru.sushishop.presentation.screens.add_render_ingredient.elements.AddRenderIngredientsTopBar
import com.chunmaru.sushishop.presentation.screens.defaults.ImageCardWithLauncher
import com.chunmaru.sushishop.presentation.screens.defaults.DefaultProgressBar
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState

@Composable
fun AddRenderIngredientScreen(
    onBackClick: () -> Unit
) {

    val viewModel: AddRenderIngredientViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current

    when (val currentState = state.value) {
        is ScreenState.Initial -> Unit
        is ScreenState.Pending -> {
            DefaultProgressBar()
        }

        is ScreenState.Success -> {
            AddRenderIngredientScreenContent(
                state = currentState.data,
                onBackClick = onBackClick,
                onImageSelected = { viewModel.changeImage(it.readBytesFromUri(context)) }
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddRenderIngredientScreenContent(
    state: CreatedIngredientWithAllIngredients,
    onBackClick: () -> Unit,
    onImageSelected: (Uri) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            AddRenderIngredientsTopBar(
                scrollBehavior = scrollBehavior,
                onBackClick = onBackClick,
                tittle = "Ingredients"
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()

        ) {

            item {
                ImageCardWithLauncher(
                    img = state.ingredient.img,
                    onImageSelected = onImageSelected,
                    modifier = Modifier
                        .size(230.dp)
                        .shadow(
                            elevation = 3.dp,
                            ambientColor = Color.Gray,
                            spotColor = Color.Gray,
                            shape = RoundedCornerShape(15.dp)
                        )
                )

            }


        }


    }


}
