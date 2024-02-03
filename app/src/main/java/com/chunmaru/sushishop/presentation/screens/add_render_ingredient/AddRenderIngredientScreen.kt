package com.chunmaru.sushishop.presentation.screens.add_render_ingredient


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chunmaru.sushishop.R
import com.chunmaru.sushishop.data.readBytesFromUri
import com.chunmaru.sushishop.presentation.screens.defaults.DefaultImageCard
import com.chunmaru.sushishop.presentation.screens.defaults.DefaultProgressBar
import com.chunmaru.sushishop.presentation.screens.defaults.DefaultTopBarItem
import com.chunmaru.sushishop.presentation.screens.defaults.ScreenState
import com.chunmaru.sushishop.ui.theme.Gray30

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
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Ingredients",
                            color = Gray30,
                            fontSize = 26.sp
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onBackground,
                    scrolledContainerColor = Color(200, 200, 200),
                    titleContentColor = MaterialTheme.colorScheme.background,
                ),
                navigationIcon = {
                    DefaultTopBarItem(
                        image = R.drawable.back, onClick = { onBackClick() },
                        paddingValues = PaddingValues(start = 8.dp)
                    )
                },
                actions = {
                    DefaultTopBarItem(
                        image = R.drawable.sort, onClick = {},
                        paddingValues = PaddingValues(end = 8.dp)
                    )
                }
            )

        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()

        ) {

            item {

                val launcher =
                    rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
                        if (uri != null) onImageSelected(uri)
                    }

                DefaultImageCard(
                    img = state.ingredient.img,
                    onClick = {
                        launcher.launch("image/*")
                    },
                    modifier = Modifier
                        .size(230.dp)
                        .shadow(
                            elevation = 3.dp,
                            ambientColor = Color.Gray,
                            spotColor = Color.Gray,
                            shape = RoundedCornerShape(15.dp)
                        ),
                )
            }


        }


    }


}
