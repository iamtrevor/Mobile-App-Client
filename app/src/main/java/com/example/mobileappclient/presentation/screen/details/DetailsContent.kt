package com.example.mobileappclient.presentation.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.Color
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.mobileappclient.Data.RoomBD.Local.Hero
import com.example.mobileappclient.R
import com.example.mobileappclient.presentation.components.InfoBox
import com.example.mobileappclient.presentation.components.OrderList
import com.example.mobileappclient.ui.theme.INFO_ICON_SIZE
import com.example.mobileappclient.ui.theme.LARGE_PADDING
import com.example.mobileappclient.ui.theme.MEDIUM_PADDING
import com.example.mobileappclient.ui.theme.MIN_SHEET_HEIGHT
import com.example.mobileappclient.ui.theme.titleColor
import com.example.mobileappclient.utils.Constants.ABOUT_TEXT_MAX_LINES
import java.nio.file.WatchEvent


@Composable
fun DetailsContent(
    navController : NavHostController,
    selectedHero : Hero?
){


    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedHero?.let { BottomSheetContent(selectedHero = it) }
        },
        content = { paddingValues ->

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


@Composable
fun BackgroundContent(
    heroImage : String,
    imageFraction : Float = 1f,
    backgroundColor : Color = MaterialTheme.colorScheme.surface,
    onCloseClicked : () -> Unit
){



}

