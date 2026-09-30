package com.example.mobileappclient.presentation.screen.details

import androidx.compose.runtime.State
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mobileappclient.utils.Constants.BASE_URL
import com.example.mobileappclient.utils.PaletteGenerator.convertImageUrlToBitmap
import com.example.mobileappclient.utils.PaletteGenerator.extractColorFromBitmap
import kotlinx.coroutines.flow.collectLatest


/*
@Composable
fun DetailsScreen(navController : NavHostController, detailsViewModel: DetailsViewModel = hiltViewModel()){


    val selectedHero by detailsViewModel.selectedHero.collectAsState()
    val colorPalette by detailsViewModel.colorPalette


    if(colorPalette.isNotEmpty()){
        DetailsContent(navController = navController, selectedHero = selectedHero, colors = colorPalette)
    } else {
        detailsViewModel.generateColorPalette()
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        detailsViewModel.uiEvent.collectLatest {  event ->
            when(event){
                is UiEvent.GenerateColorPalette -> {
                    val bitmap = convertImageUrlToBitmap(
                        imageUrl = "$BASE_URL${selectedHero?.image}",
                        context = context
                    )

                    if(bitmap != null){
                        detailsViewModel.setColorPalette(
                            colors = extractColorFromBitmap(
                                bitmap = bitmap
                            )
                        )
                    }
                }
            }
        }
    }
}

 */


@Composable
fun DetailsScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val selectedHero by detailsViewModel.selectedHero.collectAsState()
    val colorPalette by detailsViewModel.colorPalette
    val context = LocalContext.current

    // Trigger palette generation once hero data is loaded
    LaunchedEffect(key1 = selectedHero) {
        selectedHero?.let { hero ->
            val bitmap = convertImageUrlToBitmap(
                imageUrl = "$BASE_URL${hero.image}",
                context = context
            )
            if (bitmap != null) {
                detailsViewModel.setColorPalette(
                    colors = extractColorFromBitmap(bitmap = bitmap)
                )
            }
        }
    }

    if (colorPalette.isNotEmpty()) {
        DetailsContent(
            navController = navController,
            selectedHero = selectedHero,
            colors = colorPalette
        )
    }
}