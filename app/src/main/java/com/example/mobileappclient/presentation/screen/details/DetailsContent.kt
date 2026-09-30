package com.example.mobileappclient.presentation.screen.details

import android.annotation.SuppressLint
import android.graphics.Color.parseColor
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
//import androidx.compose.remote.creation.dsl.fraction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.mobileappclient.Data.RoomBD.Local.Hero
import com.example.mobileappclient.R
import com.example.mobileappclient.presentation.components.InfoBox
import com.example.mobileappclient.presentation.components.OrderList
import com.example.mobileappclient.ui.theme.EXPANDED_RADIUS_LEVEL
import com.example.mobileappclient.ui.theme.EXTRA_LARGE_PADDING
import com.example.mobileappclient.ui.theme.INFO_ICON_SIZE
import com.example.mobileappclient.ui.theme.LARGE_PADDING
import com.example.mobileappclient.ui.theme.MEDIUM_PADDING
import com.example.mobileappclient.ui.theme.MIN_SHEET_HEIGHT
import com.example.mobileappclient.ui.theme.SMALL_PADDING
import com.example.mobileappclient.ui.theme.titleColor
import com.example.mobileappclient.utils.Constants.ABOUT_TEXT_MAX_LINES
import com.example.mobileappclient.utils.Constants.BASE_URL
import com.example.mobileappclient.utils.Constants.MINIMUM_BACKGROUND_IMAGE_HEIGHT


@Composable
fun DetailsContent(
    navController : NavHostController,
    selectedHero : Hero?,
    colors : Map<String, String>
){

    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#ffffff") }


    /*
    LaunchedEffect(key1 = selectedHero) {
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!
    }

     */
    LaunchedEffect(key1 = colors) {
        vibrant = colors["vibrant"] ?: "#000000"
        darkVibrant = colors["darkVibrant"] ?: "#000000"
        onDarkVibrant = colors["onDarkVibrant"] ?: "#ffffff"
    }





    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction


    val radiusAnim by animateDpAsState(
        targetValue = if(currentSheetFraction == 1f) EXTRA_LARGE_PADDING else EXPANDED_RADIUS_LEVEL
    )



    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedHero?.let {
                BottomSheetContent(
                    selectedHero = it,
                    infoBoxIconColor = Color(parseColor(vibrant)),
                    sheetBackGroundColor = Color(parseColor(darkVibrant)),
                    contentColor = Color(parseColor(onDarkVibrant))
                )
            }
        },
        content = {
            selectedHero?.let { hero ->
                BackgroundContent(
                    heroImage = hero.image,
                    imageFraction = currentSheetFraction,
                    backgroundColor = Color(parseColor(darkVibrant)),
                    onCloseClicked = {
                        navController.popBackStack()
                    })
            }
        }
    )
}



@Composable
fun BottomSheetContent(
    selectedHero : Hero,
    infoBoxIconColor : Color = MaterialTheme.colorScheme.primary,
    sheetBackGroundColor : Color = MaterialTheme.colorScheme.surface,
    contentColor : Color = MaterialTheme.colorScheme.titleColor
){

    Column(
        modifier = Modifier.background(sheetBackGroundColor).padding(all = LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(INFO_ICON_SIZE).weight(2f),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Icon",
                tint = contentColor
            )

            Text(
                modifier = Modifier.weight(8f),
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontWeight = FontWeight.Bold
            )

        }


        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            InfoBox(
                icon = painterResource(id = R.drawable.bolt),
                iconColor = infoBoxIconColor,
                bigText = "${selectedHero.power}",
                smallText = "Power",
                textColor = contentColor
            )

            InfoBox(
                icon = painterResource(id = R.drawable.calendar),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.month,
                smallText = "Month",
                textColor = contentColor
            )

            InfoBox(
                icon = painterResource(id = R.drawable.cake),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.day,
                smallText = "Birthday",
                textColor = contentColor
            )
        }


        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "About",
            color = contentColor,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.alpha(ContentAlpha.medium).padding(bottom = MEDIUM_PADDING),
            text = selectedHero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            maxLines = ABOUT_TEXT_MAX_LINES
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            OrderList(
                title = "Family",
                items = selectedHero.family,
                textColor = contentColor
            )

            OrderList(
                title = "Abilities",
                items = selectedHero.abilities,
                textColor = contentColor
            )

            OrderList(
                title = "Nature Types",
                items = selectedHero.natureTypes,
                textColor = contentColor
            )
        }
    }
}




@SuppressLint("Range")
@Composable
fun BackgroundContent(
    heroImage : String,
    imageFraction : Float = 1f,
    backgroundColor : Color = MaterialTheme.colorScheme.surface,
    onCloseClicked : () -> Unit
){

    Box(
        modifier = Modifier.fillMaxSize().background(backgroundColor)
    ) {

        val imageUrl = "$BASE_URL${heroImage}"
        val painter = rememberImagePainter(imageUrl){
            error(R.drawable.placeholder)
        }

        Image(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + MINIMUM_BACKGROUND_IMAGE_HEIGHT)
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = "Image",
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = LARGE_PADDING, end = SMALL_PADDING),
            horizontalArrangement = Arrangement.End
        ){

            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClicked() }
            ) {

                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close icon",
                    tint = Color.White
                )
            }
        }
    }
}





@OptIn(ExperimentalMaterialApi::class)
val BottomSheetScaffoldState.currentSheetFraction : Float
    get (){
        val fraction = bottomSheetState.progress
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue



        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 1f + fraction
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            else -> fraction
        }

    }

