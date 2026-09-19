package com.example.mobileappclient.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberImagePainter
import com.example.mobileappclient.Data.RoomBD.Local.Hero
import com.example.mobileappclient.R
import com.example.mobileappclient.navigation.Screen
import com.example.mobileappclient.presentation.components.RatingWidget
import com.example.mobileappclient.ui.theme.HERO_ITEM_HEIGHT
import com.example.mobileappclient.ui.theme.LARGE_PADDING
import com.example.mobileappclient.ui.theme.MEDIUM_PADDING
import com.example.mobileappclient.ui.theme.SMALL_PADDING
import com.example.mobileappclient.utils.Constants.BASE_URL
import com.example.mobileappclient.ui.theme.topAppBarContentColor
import java.nio.file.WatchEvent
import kotlin.math.max

@Composable
fun ListContent(
    heroes : LazyPagingItems<Hero>,
    navController: NavHostController
){

}


@Composable
fun HeroItem(
    hero : Hero,
    navController: NavController
){

    val painter = rememberImagePainter(data = "$BASE_URL${hero.image}"){
        placeholder(R.drawable.placeholder)
        error(R.drawable.placeholder)
    }

    Box(modifier = Modifier.height(HERO_ITEM_HEIGHT)
        .clickable{
            navController.navigate(Screen.Details.passHeroId(heroId = hero.id))
        },
        contentAlignment = Alignment.BottomStart
    ){

        Surface(shape = MaterialTheme.shapes.large) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = "Hero Image",
                contentScale = ContentScale.Crop)
        }

        Surface(modifier = Modifier
            .fillMaxHeight(0.4f)
            .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ){
                Text(
                    text = hero.name,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.topAppBarContentColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = hero.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )


                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = hero.rating
                    )

                    Text(
                        text = "(${hero.rating})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = ContentAlpha.medium)
                    )


                }



            }

        }

    }

}
