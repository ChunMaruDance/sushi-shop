package com.chunmaru.sushishop.presentation.screens.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chunmaru.sushishop.R
import com.chunmaru.sushishop.presentation.screens.defaults.DefaultTopBar
import com.chunmaru.sushishop.ui.theme.Gray30
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen(
    navController: NavController
) {


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            DefaultTopBar(title = "Map Details",
                onMoreClick = { },
                onBackClick = { navController.popBackStack() }
            )
        },
        containerColor = MaterialTheme.colorScheme.onBackground
    ) { paddingValues ->

        val kyiv = LatLng(50.4501, 30.5234)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(kyiv, 17f)
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {

            MarkerInfoWindow(
                state = rememberMarkerState(position = kyiv),
                draggable = true,
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
            ) {


                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.roll),
                        contentDescription = "shop icon",
                        modifier = Modifier.size(27.dp)
                    )
                    Text(
                        text = "Ninja Sushi", color = Gray30,
                        fontSize = 12.sp
                    )

                }

            }

        }
    }

}