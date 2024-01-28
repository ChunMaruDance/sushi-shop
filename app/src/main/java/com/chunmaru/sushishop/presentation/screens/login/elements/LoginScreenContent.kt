package com.chunmaru.sushishop.presentation.screens.login.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.chunmaru.sushishop.R
import com.chunmaru.sushishop.presentation.screens.login.elements.DefaultButton
import com.chunmaru.sushishop.ui.theme.Gray120
import com.chunmaru.sushishop.ui.theme.Gray30

@Composable
fun LoginScreenContent(
    password: String,
    login: String,
    passwordChange: (String) -> Unit,
    loginChange: (String) -> Unit,
    checkSignIn:  () -> Unit
) {

    val passwordState = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {


        val composition = rememberLottieComposition(
            spec = LottieCompositionSpec.Asset(assetName = "loading_anim.json")
        )
        LottieAnimation(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(200.dp)
                .padding(bottom = 15.dp),
            isPlaying = true,
            composition = composition.value,
            iterations = LottieConstants.IterateForever,
            contentScale = ContentScale.FillBounds,
            speed = 0.5f
        )

        Text(
            text = "Welcome Back!",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = Color.Black
        )
        Text(
            text = "Enter your Login & Password",
            fontSize = 16.sp,
            color = Gray120
        )

        TextField(
            modifier = Modifier
                .padding(top = 6.dp)
                .fillMaxWidth(),
            value = login,
            singleLine = true,
            onValueChange = { loginChange(it) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Gray30,
                focusedIndicatorColor = Color.Black,
                cursorColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Gray120
            ),
            placeholder = { Text(text = "Login", color = Gray120) }
        )

        TextField(
            modifier = Modifier
                .padding(top = 12.dp, bottom = 12.dp)
                .fillMaxWidth(),
            value = password,
            singleLine = true,
            onValueChange = { passwordChange(it) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Gray30,
                focusedIndicatorColor = Color.Black,
                cursorColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Gray120
            ),
            visualTransformation = if (passwordState.value) VisualTransformation.None
            else PasswordVisualTransformation(),
            placeholder = { Text(text = "Password", color = Gray120) },
            trailingIcon = {
                IconButton(onClick = {
                    passwordState.value = !passwordState.value
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.eye),
                        contentDescription = "eye icon",
                        tint = Gray30,
                        modifier = Modifier.size(25.dp)
                    )

                }
            }
        )

        DefaultButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 6.dp, top = 12.dp)
                .clip(RoundedCornerShape(10))
                .background(color = Color.Transparent)
                .height(60.dp),
            title = "Sign In",
            onClick = { checkSignIn() })
        Text(
            text = "Without a password & login\n you can't log in",

            modifier = Modifier
                .padding(top = 4.dp, bottom = 30.dp)
                .align(Alignment.CenterHorizontally),
            style = TextStyle(
                lineHeight = 14.sp,
                fontSize = 12.sp,
                color = Gray120,
                textAlign = TextAlign.Center,
            )
        )


    }


}