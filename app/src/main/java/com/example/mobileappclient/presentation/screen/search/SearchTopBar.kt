package com.example.mobileappclient.presentation.screen.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.mobileappclient.ui.theme.TOP_BAR_HEIGHT
import com.example.mobileappclient.ui.theme.topAppBarBackgroundColor
import com.example.mobileappclient.ui.theme.topAppBarContentColor
import androidx.compose.foundation.layout.statusBarsPadding


@Composable
fun SearchTopBar(
    text : String,
    onTextChange : (String) -> Unit,
    onSearchClicked : (String) -> Unit,
    onCloseClicked : () -> Unit
){

    SearchWidget(
      text = text,
      onTextChange = onTextChange,
      onSearchClicked = onSearchClicked,
      onCloseClicked = onCloseClicked
    )
}



@Composable
fun SearchWidget(
    text : String,
    onTextChange : (String) -> Unit,
    onSearchClicked : (String) -> Unit,
    onCloseClicked : () -> Unit
){
    Surface(
        modifier = Modifier.fillMaxWidth().height(TOP_BAR_HEIGHT).statusBarsPadding(),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colorScheme.topAppBarBackgroundColor
    ) {
        TextField(value = text,
            onValueChange = {onTextChange(it)},
            placeholder = {
                Text(
                    modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                    text = "Search here...",
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.topAppBarContentColor
            ),
            singleLine = true,

            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = MaterialTheme.colorScheme.topAppBarContentColor
                    )
                }
            },

            trailingIcon = {
                IconButton(
                    onClick = {
                        if(text.isNotEmpty()){
                            onTextChange("")
                        } else {
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = MaterialTheme.colorScheme.topAppBarContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.topAppBarContentColor
            )
        )
    }
}