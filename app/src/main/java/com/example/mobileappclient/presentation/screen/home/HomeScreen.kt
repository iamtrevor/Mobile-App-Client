package com.example.mobileappclient.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mobileappclient.presentation.common.ListContent
import com.example.mobileappclient.presentation.components.RatingWidget
import com.example.mobileappclient.ui.theme.LARGE_PADDING

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
){


    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(onSearchedClicked = {})
        },
        content = {
            ListContent(
                heroes = allHeroes,
                navController = navController
            )
        }
    )

}