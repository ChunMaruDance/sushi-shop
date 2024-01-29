package com.chunmaru.sushishop.presentation.screens.home.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chunmaru.sushishop.R
import com.chunmaru.sushishop.ui.theme.Gray120
import com.chunmaru.sushishop.ui.theme.Gray30

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    badgeCounter: Int,
    onSearchClick: () -> Unit,
    onMoreClick: () -> Unit,
    onBasketClick: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        TopBarCard(image = R.drawable.search, onClick = onSearchClick)

        BadgedBox(
            modifier = Modifier
                .padding(top = 10.dp, end = 12.dp),
            badge = {
                Badge(
                    containerColor = if (badgeCounter == 0) Gray120 else Gray30,
                    modifier = Modifier.offset(y = 10.dp, x = (-10).dp)
                ) {
                    Text(text = badgeCounter.toString(), color = Color.White)
                }
            }
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .shadow(
                        elevation = 2.dp,
                        ambientColor = Color(204, 204, 0),
                        spotColor = Color(204, 204, 0),
                        shape = CircleShape
                    )
                    .background(Color(247, 247, 247)),
                contentAlignment = Alignment.Center,
            ) {
                IconButton(onClick = { onBasketClick() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.basket),
                        contentDescription = "menu item",
                        tint = Gray30,
                        modifier = Modifier
                            .size(25.dp)
                    )
                }
            }

        }

        TopBarCard(image = R.drawable.sort, onClick = onMoreClick)

    }

}


@Composable
fun TopBarCard(
    image: Int,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .padding(top = 10.dp, end = 12.dp)
            .size(42.dp)
            .shadow(
                elevation = 2.dp,
                ambientColor = Color(204, 204, 0),
                spotColor = Color(204, 204, 0),
                shape = CircleShape
            )
            .background(Color(247, 247, 247)),
        contentAlignment = Alignment.Center,
    ) {
        IconButton(onClick = { onClick() }) {
            Icon(
                painter = painterResource(id = image),
                contentDescription = "menu item",
                tint = Gray30,
                modifier = Modifier
                    .size(25.dp)
            )
        }


    }


}