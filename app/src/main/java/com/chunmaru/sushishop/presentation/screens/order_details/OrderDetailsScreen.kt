package com.chunmaru.sushishop.presentation.screens.order_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieConstants
import com.chunmaru.sushishop.R
import com.chunmaru.sushishop.presentation.screens.defaults.DefaultTopBar
import com.chunmaru.sushishop.presentation.screens.order_details.elements.BottomButton
import com.chunmaru.sushishop.presentation.screens.order_details.elements.DefaultAlertDialog
import com.chunmaru.sushishop.ui.theme.Gray120
import com.chunmaru.sushishop.ui.theme.Gray30
import com.chunmaru.sushishop.ui.theme.Gray70

@Composable
fun OrderDetailsScreen(
    navController: NavController,
    onMapScreen: () -> Unit,
    onCompleteOrder: () -> Unit
) {

    val viewModel: OrderDetailsScreenViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()


    LaunchedEffect(key1 = true) {
        viewModel.initController(navController)
        viewModel.initState()
    }

    when (val currentState = state.value) {
        OrderDetailsScreenState.Initial -> {}
        OrderDetailsScreenState.Pending -> {}
        is OrderDetailsScreenState.ShowData -> {

            val username = remember {
                mutableStateOf(currentState.usersData.username)
            }

            val phoneNumber = remember {
                mutableStateOf(currentState.usersData.phone)
            }

            val suggestions = currentState.usersData.addresses

            val location = remember {
                mutableStateOf(
                    if (currentState.usersData.addresses.isNotEmpty())
                        currentState.usersData.addresses[0] else ""
                )
            }


            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.onBackground)
                    .fillMaxSize()

            ) {

                DefaultTopBar(title = "Order Details",
                    onMoreClick = { },
                    onBackClick = { navController.popBackStack() }
                )

                Text(
                    text = "Shop Location",
                    color = Gray30,
                    fontSize = 21.sp,
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp, top = 10.dp),
                ) {

                    Box(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .clip(RoundedCornerShape(30))
                            .clickable {
                                onMapScreen()
                            }
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.google_maps),
                            contentDescription = "google maps image",
                            Modifier.size(170.dp, 100.dp),
                            contentScale = ContentScale.FillWidth
                        )

                    }

                    Column {

                        Text(
                            text = "Kyiv",
                            color = Gray30,
                            fontSize = 18.sp,
                        )
                        Text(
                            text = "Київ, Хрещатик, 1",
                            color = Gray120,
                            fontSize = 12.sp,
                            maxLines = 3
                        )


                    }

                }


                Text(
                    text = "Info",
                    color = Gray30,
                    fontSize = 21.sp,
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .align(Alignment.CenterHorizontally)
                )



                OutlinedTextField(
                    value = username.value,
                    onValueChange = {
                        username.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 3.dp, start = 12.dp, end = 12.dp),
                    label = { Text(text = "Name") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "name icon",
                            tint = Gray30
                        )

                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedLabelColor = Gray120,
                        cursorColor = Gray30,
                        focusedLabelColor = Gray30,
                        focusedTextColor = Gray30,
                        unfocusedTextColor = Gray120,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Gray30,
                        unfocusedIndicatorColor = Gray120
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                    ),
                )


                var expanded by remember { mutableStateOf(false) }


                val icon = if (expanded)
                    Icons.Filled.KeyboardArrowDown
                else
                    Icons.Filled.KeyboardArrowUp


                Box {
                    OutlinedTextField(
                        value = location.value,
                        onValueChange = { location.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp, top = 12.dp),
                        label = { Text("Location") },
                        trailingIcon = {
                            Icon(icon, "contentDescription",
                                Modifier.clickable { expanded = !expanded })
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "location icon",
                                tint = Gray30
                            )

                        },
                        colors = TextFieldDefaults.colors(
                            cursorColor = Gray30,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Black,
                            focusedLabelColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Gray120,
                            unfocusedIndicatorColor = Gray120,
                            unfocusedLabelColor = Gray120
                        )
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp)
                    ) {
                        suggestions.forEach { label ->
                            DropdownMenuItem(onClick = {
                                location.value = label
                            }) {
                                Row(
                                    modifier = Modifier
                                        .padding(horizontal = 7.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        text = label,
                                        color = Gray30,
                                        fontSize = 16.sp,
                                        modifier = Modifier.weight(1f)
                                    )
                                    IconButton(onClick = {
                                        viewModel.deleteLocation(label)
                                    }) {

                                        Icon(
                                            painter = painterResource(id = R.drawable.delete),
                                            contentDescription = "delete image",
                                            tint = Gray30,
                                            modifier = Modifier.size(25.dp)
                                        )

                                    }


                                }
                            }
                        }
                    }
                }
                //

                OutlinedTextField(
                    value = phoneNumber.value,
                    onValueChange = {
                        phoneNumber.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, start = 12.dp, end = 12.dp),
                    label = { Text(text = "Phone number") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Email icon",
                            tint = Gray30
                        )

                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedLabelColor = Gray120,
                        cursorColor = Gray30,
                        focusedLabelColor = Gray30,
                        focusedTextColor = Gray30,
                        unfocusedTextColor = Gray120,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Gray30,
                        unfocusedIndicatorColor = Gray120
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Phone,
                    ),
                )


                Divider(
                    color = Gray120,
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 12.dp)
                )


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Total", fontSize = 21.sp,
                        color = Gray70,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        modifier = Modifier.padding(top = 12.dp),
                        text = buildAnnotatedString {
                            append(currentState.price)
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 13.sp,
                                    baselineShift = BaselineShift.Superscript,
                                )
                            ) {
                                append("$")
                            }

                        },
                        color = Gray30,
                        fontSize = 21.sp,
                    )

                }



                Text(
                    text = "Payment upon receipt !!!",
                    modifier = Modifier.padding(start = 12.dp),
                    color = Gray120,
                    fontSize = 16.sp
                )

                val orderState = remember {
                    mutableStateOf<OrderState>(OrderState.Initial)
                }


                BottomButton(title = "Payment") {
                    viewModel.orderComplete(
                        userName = username.value,
                        phoneNumber = phoneNumber.value,
                        location = location.value,
                        onState = { orderState.value = it }
                    )
                }

                when (orderState.value) {

                    OrderState.Initial -> {}
                    OrderState.NoValidData -> {

                        DefaultAlertDialog(
                            animAssets = "error_anim.json",
                            message = "Some your data is not valid\n" +
                                    "Please fix that",
                            showDialog = true,
                            onDismiss = {
                                orderState.value = OrderState.Initial
                            }
                        )
                    }

                    OrderState.ShortageGoods -> {

                        DefaultAlertDialog(
                            animAssets = "error_anim.json",
                            message = "Unfortunately, some products\n" +
                                    "are out of stock",
                            showDialog = true,
                            onDismiss = {
                                orderState.value = OrderState.Initial
                            }
                        )

                    }

                    OrderState.SuccessOrder -> {
                        DefaultAlertDialog(
                            iteration = 1,
                            animAssets = "success_anim.json",
                            message = "The order has been\n" +
                                    "successfully processed",
                            showDialog = true,
                            onDismiss = {
                                orderState.value = OrderState.Initial
                                onCompleteOrder()

                            }
                        )
                    }

                    OrderState.Loading -> {
                        DefaultAlertDialog(
                            iteration = LottieConstants.IterateForever,
                            animAssets = "loading_anim.json",
                            message = "Loading\nPlease wait",
                            showDialog = true,
                            onDismiss = {

                            }
                        )
                    }
                }


            }


        }
    }


}