package com.chunmaru.sushishop.presentation.screens.splash

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.size.Size
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SplashScreen(
    onHome: () -> Unit
) {

    val bannerData = remember {
        BannerData.DEFAULT
    }
    val scope = rememberCoroutineScope()

    val linearGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(217, 217, 217),
            Color(230, 230, 230),
            Color(242, 242, 242),
            Color(255, 255, 255),

            )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {

        Text(
            text = "Ninja Sushi",
            fontSize = 32.sp,
            fontFamily = FontFamily.Serif,
            style = TextStyle(
                brush = linearGradient
            ),
            modifier = Modifier.padding(top = 24.dp)
        )

        val pagerState = rememberPagerState(initialPage = 1)

        Column {
            HorizontalPager(
                count = bannerData.size,
                state = pagerState,
                modifier = Modifier.height(320.dp),
                contentPadding = PaddingValues(horizontal = 60.dp)
            ) { page ->

                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
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
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }

                ) {

                    Image(
                        painter = painterResource(id = bannerData[page].img),
                        contentDescription = "banner image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                }

            }

            Row(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .height(50.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(bannerData.size) {
                    val color = if (pagerState.currentPage == it) Color(255, 255, 255)
                    else Color(191, 191, 191)
                    val targetSize = if (pagerState.currentPage == it) 15.dp else 10.dp

                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                            .height(10.dp)
                            .width(targetSize)
                            .background(color)
                            .clickable {
                                scope.launch {
                                    pagerState.animateScrollToPage(it)
                                }
                            }
                            .animateContentSize(
                                animationSpec = tween(durationMillis = 300)
                            )
                    ) {}
                }

            }
        }

        Text(
            text = "Embark on a Culinary Odyssey with Ninja Sushi: Where Flavor Meets Stealth in Every Bite!",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 14.dp, bottom = 7.dp)
                    .clip(RoundedCornerShape(20))
                    .background(color = Color.Transparent)
                    .height(60.dp)
                    .clickable {
                        onHome()
                    },
                colors = CardDefaults.cardColors(
                    containerColor =  MaterialTheme.colorScheme.onBackground
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 2.dp
                )
            ) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    androidx.compose.material.Text(
                        text = "Get Started",
                        color = MaterialTheme.colorScheme.background,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                }


            }
        }

    }


}