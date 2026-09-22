package com.example.mobileappclient.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.LoadState
import com.example.mobileappclient.R
import com.example.mobileappclient.ui.theme.DarkGray
import com.example.mobileappclient.ui.theme.LightGray
import com.example.mobileappclient.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import com.example.mobileappclient.ui.theme.SMALL_PADDING

@Composable
fun EmptyScreen(error : LoadState.Error){

    val message by remember {
        mutableStateOf(parseErrorMessage(message = error.toString()))
    }

    val icon by remember {
        mutableStateOf(R.drawable.network_error)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(NETWORK_ERROR_ICON_HEIGHT),
            painter = painterResource(id = icon),
            contentDescription = "Image Icon",
            tint = if(isSystemInDarkTheme()) LightGray else DarkGray
        )

        Text(
            modifier = Modifier.padding(top = SMALL_PADDING),
            text = message,
            color = if(isSystemInDarkTheme()) LightGray else DarkGray,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )

    }

}



fun parseErrorMessage(message : String): String{
    return when {
        message.contains("SocketTimeoutException") -> {
            "Server Unavailable"
        }
        message.contains("ConnectionException") -> {
            "Internet Unavailable"
        }
        else -> {
            "Unknown Error"
        }
    }
}