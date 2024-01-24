package com.chunmaru.sushishop.presentation.screens.splash

import com.chunmaru.sushishop.R

data class BannerData(
    val img: Int
) {

    companion object {
        val DEFAULT = listOf(
            BannerData(img = R.drawable.pic1),
            BannerData(img = R.drawable.pic2),
            BannerData(img = R.drawable.pic3),
            BannerData(img = R.drawable.pic4),

        )
    }

}