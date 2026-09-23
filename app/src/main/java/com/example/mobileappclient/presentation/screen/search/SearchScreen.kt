package com.example.mobileappclient.presentation.screen.search

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mobileappclient.presentation.screen.home.HomeTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController : NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
){

    val searchQuery by searchViewModel.searchQuery

    Scaffold(
        topBar = {
            SearchTopBar(
              text = searchQuery,
              onTextChange = {
                  searchViewModel.updateSearchQuery(query = it)
              },
              onSearchClicked = {},
              onCloseClicked = {
                  navController.popBackStack()
              }
            )
        }
    ) { }
}