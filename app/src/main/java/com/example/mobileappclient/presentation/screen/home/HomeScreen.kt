package com.example.mobileappclient.presentation.screen.home


import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mobileappclient.presentation.common.ListContent




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
        content = { paddingValues ->
            ListContent(
                heroes = allHeroes,
                navController = navController,
                paddingValues = paddingValues
            )
        }
    )

}


