package com.chunmaru.sushishop.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chunmaru.sushishop.ui.theme.Gray120
import com.chunmaru.sushishop.ui.theme.Gray30
import com.chunmaru.sushishop.ui.theme.Gray70

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {


    val viewModel: SearchScreenViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()

    val text = remember {
        mutableStateOf("")
    }
    val active = remember {
        mutableStateOf(false)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
            .background(MaterialTheme.colorScheme.onBackground)
    ) {

        DockedSearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 8.dp)
                .semantics { traversalIndex = -1f },
            query = text.value,
            onQueryChange = { text.value = it },
            onSearch = {
                active.value = false
            },
            active = active.value,
            onActiveChange = {
                active.value = it
            },
            placeholder = { Text(text = "Search...", color = Gray120) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search icon",
                    tint = Gray120
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "more icon",
                    tint = Gray120
                )
            },
            colors = SearchBarDefaults.colors(
                containerColor = Color(247, 247, 247)
            )
        ) {
            //todo
        }

        LazyColumn(
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 72.dp)
        ) {
            //todo
        }


    }


}