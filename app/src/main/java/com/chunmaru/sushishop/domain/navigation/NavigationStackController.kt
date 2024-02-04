package com.chunmaru.sushishop.domain.navigation

import android.os.Parcelable
import android.util.Log
import androidx.core.os.bundleOf
import androidx.navigation.NavController


class NavigationStackController {


    fun <T> putInPreviewBackStack(
        navController: NavController,
        data: T,
        bundleKey: String,
        valueKey: String
    ){
        navController.previousBackStackEntry?.arguments?.putBundle(
            bundleKey,
            bundleOf(
                valueKey to data
            )
        )

        Log.d("MyTag", "putInPreviewBackStack: complete")
    }

    fun <T : Parcelable> getCurrentBackStackDataArray(
        navController: NavController,
        bundleKey: String,
        valueKey: String,
    ): List<T>?{
        return navController.currentBackStackEntry?.arguments?.getBundle(bundleKey)
            ?.let { bundle -> (bundle.getParcelableArrayList(valueKey)) }
    }


    fun <T> putInCurrentBackStack(
        navController: NavController,
        data: T,
        bundleKey: String,
        valueKey: String
    ) {
        navController.currentBackStackEntry?.arguments?.putBundle(
            bundleKey,
            bundleOf(
                valueKey to data
            )
        )

        Log.d("MyTag", "putInCurrentBackStack: complete")

    }

    fun <T> getPreviewBackStackData(
        navController: NavController,
        bundleKey: String,
        valueKey: String,
    ): T? {
        return navController.previousBackStackEntry?.arguments?.getBundle(bundleKey)
            ?.let { bundle ->
                (bundle.getParcelable<Parcelable>(valueKey)
                        as? T)?.also { dish ->
                    Log.d("MyTag", "getPreviewBackStackData: complete")
                }
            }
    }

    fun <T : Parcelable> getPreviewBackStackDataArray(
        navController: NavController,
        bundleKey: String,
        valueKey: String,
    ): List<T>? {
        return navController.previousBackStackEntry?.arguments?.getBundle(bundleKey)
            ?.let { bundle -> (bundle.getParcelableArrayList(valueKey)) }
    }



}