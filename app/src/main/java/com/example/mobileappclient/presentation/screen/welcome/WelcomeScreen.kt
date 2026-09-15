package com.example.mobileappclient.presentation.screen.welcome


import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mobileappclient.navigation.Screen
import com.example.mobileappclient.presentation.screen.OnBoardingPage
import com.example.mobileappclient.ui.theme.EXTRA_LARGE_PADDING
import com.example.mobileappclient.ui.theme.PAGING_INDICATOR_SPACING
import com.example.mobileappclient.ui.theme.PAGING_INDICATOR_WIDTH
import com.example.mobileappclient.ui.theme.SMALL_PADDING
import com.example.mobileappclient.ui.theme.activeIndicatorColor
import com.example.mobileappclient.ui.theme.buttonBackgroundColors
import com.example.mobileappclient.ui.theme.descriptionColor
import com.example.mobileappclient.ui.theme.isInactiveIndicatorColor
import com.example.mobileappclient.ui.theme.titleColor
import com.example.mobileappclient.ui.theme.welcomeScreenBackGroundColor
import com.example.mobileappclient.utils.Constants.LAST_ONBOARDING_PAGE
import com.example.mobileappclient.utils.Constants.ON_BOARDING_PAGE_COUNT
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import java.nio.file.WatchEvent


@Composable
fun WelcomeScreen(
    welcomeViewModel: WelcomeViewModel = hiltViewModel(),
    navController: NavHostController) {

    val pagerState = rememberPagerState()

    //variable containing a list of all the onboarding pages
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    Column(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.welcomeScreenBackGroundColor)
    ) {

        HorizontalPager(
            modifier = Modifier.weight(10f),
            state = pagerState,
            count = ON_BOARDING_PAGE_COUNT,
            verticalAlignment = Alignment.Top) { position ->

                PagerScreen(onBoardingPage = pages[position])
        }

        HorizontalPagerIndicator(
            modifier = Modifier.weight(1f).align(Alignment.CenterHorizontally),
            pagerState = pagerState,
            activeColor = MaterialTheme.colorScheme.activeIndicatorColor,
            inactiveColor = MaterialTheme.colorScheme.isInactiveIndicatorColor,
            indicatorWidth = PAGING_INDICATOR_WIDTH,
            spacing = PAGING_INDICATOR_SPACING
        )


        FinishButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState){

            navController.popBackStack()
            navController.navigate(Screen.Home.route)
            welcomeViewModel.saveOnBoardingState(completed = true)
        }

    }

}



@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage){

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Image(modifier = Modifier.fillMaxWidth(0.5f).fillMaxHeight(0.7f),painter = painterResource(id = onBoardingPage.image), contentDescription = "on boarding image")

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = onBoardingPage.title,
            color = MaterialTheme.colorScheme.titleColor,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )


        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            text = onBoardingPage.description,
            color = MaterialTheme.colorScheme.descriptionColor,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}



@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState : PagerState,
    onClick : () -> Unit
){
    Row(
        modifier = modifier.padding(horizontal = EXTRA_LARGE_PADDING),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {

        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == LAST_ONBOARDING_PAGE
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.buttonBackgroundColors,
                    contentColor = Color.White
                )) {
                Text(text = "Finish")
            }
        }


    }
}
