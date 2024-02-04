package com.chunmaru.sushishop

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.chunmaru.sushishop.data.storage.DataStoreManager
import com.chunmaru.sushishop.domain.navigation.ScreenNavigator
import com.chunmaru.sushishop.ui.theme.SushiShopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SushiShopTheme {
                val context = LocalContext.current
                LaunchedEffect(true) {

//                    val dataStore = DataStoreManager(context)
//                    dataStore.removeToken()
//                    Log.d("MyTag", "onCreate: ${dataStore.getAdminToken()} ")

                }

                ScreenNavigator()
            }
        }
    }

}


