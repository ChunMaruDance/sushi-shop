package com.chunmaru.sushishop.presentation.screens.home.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.chunmaru.sushishop.R
import com.chunmaru.sushishop.data.models.dishes.TestDish
import com.chunmaru.sushishop.ui.theme.Gray30
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreenCarousel(
    dishes: List<TestDish>,
    addToBasket: (TestDish) -> Unit,
    onItemClick: (TestDish) -> Unit
) {


    val pagerState = rememberPagerState(initialPage = 1)

    HorizontalPager(
        count = dishes.size,
        state = pagerState,
        modifier = Modifier
            .padding(top = 8.dp)
            .height(340.dp),
        contentPadding = PaddingValues(horizontal = 65.dp)
    ) { page ->

        Card(
            shape = RoundedCornerShape(10),
            modifier = Modifier
                .clip(RoundedCornerShape(10))
                .background(Color.Transparent)
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        start = 0.8f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .clickable {
                    onItemClick(dishes[page])
                },
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(242, 242, 242),
                                Color(250, 250, 250)
                            )
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                HomeCarouselCard(
                    dish = dishes[page],
                    addToBasket = addToBasket
                )


            }


        }

    }


}

@Composable
fun ColumnScope.HomeCarouselCard(
    dish: TestDish,
    addToBasket: (TestDish) -> Unit
) {

    Image(
        painter = painterResource(id = dish.image),
        contentDescription = "banner image",
        modifier = Modifier
            .padding(vertical = 20.dp)
            .size(180.dp),
        contentScale = ContentScale.Crop
    )

    Text(
        text = dish.category,
        fontSize = 14.sp,
        modifier = Modifier
            .padding(start = 8.dp)
            .align(Alignment.Start)
            .offset(y = (-5).dp),
        color = Color(204, 204, 0)
    )

    Text(
        text = dish.name,
        fontSize = 18.sp,
        modifier = Modifier
            .padding(start = 8.dp)
            .align(Alignment.Start)
            .offset(y = (-5).dp),
        color = Gray30
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 13.sp,
                        baselineShift = BaselineShift.Superscript,
                    )
                ) {
                    append("$")
                }
                append(dish.price.toString())
            },
            color = Gray30,
            fontSize = 20.sp,
        )

        IconButton(onClick = { addToBasket(dish) }) {
            Icon(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "add to Basket",
                modifier = Modifier.size(40.dp)
            )

        }


    }


}
