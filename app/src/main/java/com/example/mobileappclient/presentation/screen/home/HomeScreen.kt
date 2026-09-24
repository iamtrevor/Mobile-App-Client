package com.example.mobileappclient.presentation.screen.home


import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mobileappclient.navigation.Screen
import com.example.mobileappclient.presentation.common.ListContent




@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
){


    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(onSearchedClicked = {
                navController.navigate(Screen.Search.route)
            })
        },
        content = { paddingValues ->
            ListContent(
                heroes = allHeroes,
                navController = navController,
                modifier = Modifier.padding(paddingValues)
            )
        }
    )

}


