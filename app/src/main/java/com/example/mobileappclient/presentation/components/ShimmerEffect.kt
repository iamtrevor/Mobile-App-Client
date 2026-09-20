package com.example.mobileappclient.presentation.components

import android.util.Log
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import com.example.mobileappclient.Data.RoomBD.Local.Hero
import com.example.mobileappclient.ui.theme.ABOUT_PLACEHOLDER_HEIGHT
import com.example.mobileappclient.ui.theme.EXTRA_LARGE_PADDING
import com.example.mobileappclient.ui.theme.EXTRA_SMALL_PADDING

import com.example.mobileappclient.ui.theme.HERO_ITEM_HEIGHT
import com.example.mobileappclient.ui.theme.LARGE_PADDING
import com.example.mobileappclient.ui.theme.MEDIUM_PADDING
import com.example.mobileappclient.ui.theme.NAME_PLACEHOLDER_HEIGHT
import com.example.mobileappclient.ui.theme.RATING_PLACEHOLDER_HEIGHT
import com.example.mobileappclient.ui.theme.SMALL_PADDING
import com.example.mobileappclient.ui.theme.ShimmerDarkGray
import com.example.mobileappclient.ui.theme.ShimmerLightGray
import com.example.mobileappclient.ui.theme.ShimmerMediumGray

@Composable
fun ShimmerEffect(paddingValues: PaddingValues) {

    Log.d("SHIMMER", "ShimmerEffect COMPOSED")



    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ) {

        items(count = 2){
            AnimatedShimmerItem()
        }

    }

}






@Composable
fun AnimatedShimmerItem(){

    val transition = rememberInfiniteTransition()

    val alphaAnim by transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 800,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    ShimmerItem(alpha = alphaAnim)
}




@Composable
fun ShimmerItem(alpha : Float){
    Surface(
        modifier = Modifier.fillMaxWidth().height(HERO_ITEM_HEIGHT),
        color = Color.Blue,//if (isSystemInDarkTheme()) Color.Black else ShimmerLightGray,
        shape = RoundedCornerShape(size = LARGE_PADDING)
    ) {

        Column(
            modifier = Modifier.padding(all = MEDIUM_PADDING),
            verticalArrangement = Arrangement.Bottom
        ) {

            Surface(
                modifier = Modifier.alpha(alpha = alpha).fillMaxWidth(0.5f).height(NAME_PLACEHOLDER_HEIGHT),
                color = if (isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                shape = RoundedCornerShape(size = SMALL_PADDING)
            ) { }

            Spacer(modifier = Modifier.padding(all = MEDIUM_PADDING))

            repeat(3){
                Surface(
                    modifier = Modifier.alpha(alpha = alpha).fillMaxWidth().height(ABOUT_PLACEHOLDER_HEIGHT),
                    color = if (isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(size = EXTRA_LARGE_PADDING)
                ) { }
                Spacer(modifier = Modifier.height(SMALL_PADDING))
            }



            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(5){
                    Surface(
                        modifier = Modifier.alpha(alpha = alpha).size(RATING_PLACEHOLDER_HEIGHT),
                        color = if (isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                        shape = RoundedCornerShape(size = EXTRA_SMALL_PADDING)
                    ) { }
                    Spacer(modifier = Modifier.padding(all = SMALL_PADDING))
                }
            }

        }

    }
}




@Preview
@Composable
fun ShimmerItemPreview(){
    ShimmerItem(alpha = 1f)
}


