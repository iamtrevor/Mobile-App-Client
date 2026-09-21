package com.example.mobileappclient.presentation.screen.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.example.mobileappclient.ui.theme.topAppBarBackgroundColor
import com.example.mobileappclient.ui.theme.topAppBarContentColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(onSearchedClicked : () -> Unit){

    TopAppBar(
        title = {
            Text(
                text = "Explore",
                color = MaterialTheme.colorScheme.topAppBarContentColor
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.topAppBarBackgroundColor
        ),

        actions = {
            IconButton(onClick = onSearchedClicked) {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = "Search icon")
            }
        }

    )




}
