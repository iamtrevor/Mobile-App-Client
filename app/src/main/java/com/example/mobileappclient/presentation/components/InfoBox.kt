package com.example.mobileappclient.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileappclient.R
import com.example.mobileappclient.ui.theme.INFO_ICON_SIZE
import com.example.mobileappclient.ui.theme.SMALL_PADDING
import com.example.mobileappclient.ui.theme.titleColor

@Composable
fun InfoBox(
    icon : Painter,
    iconColor : Color,
    bigText : String,
    smallText : String,
    textColor : Color
){

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(end = SMALL_PADDING).size(INFO_ICON_SIZE),
            painter = icon,
            contentDescription = "Icon",
            tint = iconColor
        )

        Column() {
            Text(
                text = bigText,
                color = textColor,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Black
            )
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = smallText,
                color = textColor,
                fontSize = MaterialTheme.typography.labelSmall.fontSize
            )
        }


    }

}

//preview
@Composable
@Preview(showBackground = true)
fun InfoBoxPreview(){
    InfoBox(
        icon = painterResource(id = R.drawable.bolt),
        iconColor = MaterialTheme.colorScheme.primary,
        bigText = "32",
        smallText = "Power",
        textColor = MaterialTheme.colorScheme.titleColor
    )
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun InfoBoxDarkModePreview(){
    InfoBox(
        icon = painterResource(id = R.drawable.bolt),
        iconColor = MaterialTheme.colorScheme.primary,
        bigText = "32",
        smallText = "Power",
        textColor = MaterialTheme.colorScheme.titleColor
    )
}