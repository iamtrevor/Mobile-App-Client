package com.example.mobileappclient.presentation.screens.components


import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.example.mobileappclient.presentation.components.RatingWidget
import com.example.mobileappclient.ui.theme.SMALL_PADDING
import org.junit.Rule
import org.junit.Test

class RatingWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun passZeroPointZeroValue_Assert_FiveEmptyStars(){
        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 0.0
            )
        }

        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(5)

        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)

        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)
    }



    @Test
    fun passZeroPointFiveValue_Assert_FourEmptyStars_OneHalfFilledStar(){
        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 0.5
            )
        }

        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(4)

        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(1)

        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)

    }

    @Test
    fun passZeroPointSixValue_Assert_FourEmptyStars_and_oneFilledStar(){
        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 0.6
            )
        }

        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(4)

        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)

        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(1)

    }



    @Test
    fun passFourPointFiveValue_Assert_FourEmptyStars_OneHalfFilledStar(){
        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 4.5
            )
        }

        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(0)

        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(1)

        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(4)

    }

    //testing with negative values
    @Test
    fun passNegative_Assert_FiveEmptyStars(){
        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = -1.5
            )
        }

        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(5)

        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)

        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)

    }


    //test with invalid values
    @Test
    fun passInvalidValue_Assert_FiveEmptyStars(){
        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 5.1
            )
        }

        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(5)

        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)

        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)

    }






}