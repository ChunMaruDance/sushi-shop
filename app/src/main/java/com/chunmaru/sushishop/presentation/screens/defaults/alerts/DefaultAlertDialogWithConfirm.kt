package com.chunmaru.sushishop.presentation.screens.defaults.alerts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.chunmaru.sushishop.ui.theme.Gray30

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAlertDialogWithConfirm(
    showDialog: Boolean,
    animAssets: String,
    message: String,
    iteration: Int = LottieConstants.IterateForever,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {

    val composition = rememberLottieComposition(
        spec = LottieCompositionSpec.Asset(assetName = animAssets)
    )

    if (showDialog) {
        AlertDialog(onDismissRequest = { onDismiss() }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, end = 6.dp)
                    .clip(RoundedCornerShape(10)),
                shape = RoundedCornerShape(10),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onBackground
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    LottieAnimation(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth(),
                        isPlaying = true,
                        composition = composition.value,
                        iterations = iteration,
                        contentScale = ContentScale.FillWidth
                    )
                    Text(
                        text = message,
                        color = Gray30,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 26.dp, top = 2.dp),
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Serif
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Card(
                            modifier = Modifier
                                .shadow(
                                    elevation = 2.dp,
                                    ambientColor = Color(204, 204, 0),
                                    spotColor = Color(204, 204, 0),
                                    shape = RoundedCornerShape(20)
                                )
                                .clickable { onDismiss() },
                            colors = CardDefaults.cardColors(
                                containerColor = Color(247, 247, 247)
                            ),
                            shape = RoundedCornerShape(20)
                        ) {
                            Text(
                                text = "Cancel",
                                modifier = Modifier.padding(8.dp),
                                fontSize = 16.sp,
                                fontFamily = FontFamily.Serif
                            )
                        }

                        Card(
                            modifier = Modifier
                                .shadow(
                                    elevation = 2.dp,
                                    ambientColor = Color(204, 204, 0),
                                    spotColor = Color(204, 204, 0),
                                    shape = RoundedCornerShape(20)
                                )
                                .clickable { onConfirm() },
                            colors = CardDefaults.cardColors(
                                containerColor = Color(247, 247, 247)
                            ),
                            shape = RoundedCornerShape(20)
                        ) {
                            Text(
                                text = "Confirm",
                                modifier = Modifier.padding(8.dp),
                                fontSize = 16.sp,
                                fontFamily = FontFamily.Serif
                            )
                        }


                    }


                }


            }


        }
    }


}