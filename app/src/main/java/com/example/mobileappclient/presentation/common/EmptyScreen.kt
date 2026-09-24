package com.example.mobileappclient.presentation.common


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import java.io.IOException
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.mobileappclient.Data.RoomBD.Local.Hero
import com.example.mobileappclient.R
import com.example.mobileappclient.ui.theme.DarkGray
import com.example.mobileappclient.ui.theme.LightGray
import com.example.mobileappclient.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import com.example.mobileappclient.ui.theme.SMALL_PADDING
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException



@Composable
fun EmptyScreen(error : LoadState.Error ?= null,
                heroes : LazyPagingItems<Hero>? = null
){

    var message by remember(error) {
        mutableStateOf("Find Your Favourite Hero")
    }

    var icon by remember {
        mutableStateOf(R.drawable.search_document)
    }

    if(error != null){
        message = parseErrorMessage(error = error)
        icon = R.drawable.network_error
    }

    var startAnimation by remember { mutableStateOf(false) }

    val alphaAnim by animateFloatAsState(
        targetValue = if(startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(
        alphaAnim = alphaAnim,
        icon = icon,
        message = message,
        heroes = heroes,
        error = error
    )


}


@Composable
fun EmptyContent(alphaAnim : Float,
                 icon : Int,
                 message: String,
                 heroes : LazyPagingItems<Hero>? = null,
                 error : LoadState.Error ?= null){

    var isRefreshing by remember { mutableStateOf(false) }



    SwipeRefresh(
        swipeEnabled = error != null,
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            heroes?.refresh()
            isRefreshing = false
        }) {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(NETWORK_ERROR_ICON_HEIGHT)
                    .alpha(alpha = alphaAnim),
                painter = painterResource(id = icon),
                contentDescription = "Image Icon",
                tint = if(isSystemInDarkTheme()) LightGray else DarkGray
            )

            Text(
                modifier = Modifier.padding(top = SMALL_PADDING)
                    .alpha(alpha = alphaAnim),
                text = message,
                color = if(isSystemInDarkTheme()) LightGray else DarkGray,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )

        }
    }
}



// Update to take a Throwable and use 'is' checks

fun parseErrorMessage(error: LoadState.Error): String {
    return when (error) {
        is SocketTimeoutException -> {
            "Server Unavailable"
        }
        is ConnectException -> {
            // Connection Refused means the server is likely down or port is wrong
            "Server Unavailable"
        }
        is UnknownHostException -> {
            // DNS failure or no network route usually means no internet
            "Internet Unavailable"
        }
        is IOException -> {
            if (error.message?.contains("unexpected end of stream") == true) {
                "Server Unavailable"
            } else {
                "Internet Unavailable"
            }
        }
        else -> {
            "Unknown Error"
        }
    }
}