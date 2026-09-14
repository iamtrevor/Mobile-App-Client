package com.example.mobileappclient.presentation.screen

import androidx.annotation.DrawableRes
import com.example.mobileappclient.R

sealed class OnBoardingPage (

    //tells kotlin this is supposed to be a drawable resource ID
    @DrawableRes
    val image : Int,
    val title : String,
    val description : String
) {

    object First : OnBoardingPage(
        image = R.drawable.greetings,
        title = "Greetings",
        description = "Are you a Hero Fan? Because iif you are there is great news for you"
    )

    object Second : OnBoardingPage(
        image = R.drawable.explore,
        title = "Explore",
        description = "Find your favorite hero and learn some things you didn't know about"
    )

    object Third : OnBoardingPage(
        image = R.drawable.power,
        title = "Power",
        description = "Check out your hero's power and see how much are they are strong compared to others"
    )



}