package com.example.mobileappclient.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.mobileappclient.presentation.screen.search.SearchWidget
import org.junit.Rule
import org.junit.Test

class SearchWidgetTest {

    @get : Rule
    val composeTestRule = createComposeRule()

    @Test
    fun openSearchWidget_addInputText_assertInputText() {

        val text =   mutableStateOf("")
        composeTestRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChange = {
                    text.value = it },
                onCloseClicked = {},
                onSearchClicked = {}
            )
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("TreeBees")

        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextEquals("TreeBees")
    }


    @SuppressLint("UnrememberedMutableState")
    @Test
    fun openSearchWidget_addInputText_pressCloseButton_assertEmptyInputText() {
        var searchWidgetShown = mutableStateOf(false)
        val text = mutableStateOf("")
        composeTestRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChange = {
                    text.value = it },
                onCloseClicked = {
                    searchWidgetShown.value = false
                },
                onSearchClicked = {}
            )
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("TreeBees")

        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()

        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextContains("")
    }


    @SuppressLint("UnrememberedMutableState")
    @Test
    fun openSearchWidget_addInputText_pressCloseButtonTwice_assertClosedState() {

        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)

        composeTestRule.setContent {
            if(searchWidgetShown.value){
                SearchWidget(
                    text = text.value,
                    onTextChange = {
                        text.value = it },
                    onCloseClicked = {
                        searchWidgetShown.value = false
                    },
                    onSearchClicked = {}
                )
            }
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("TreeBees")

        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()

        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()

        composeTestRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }


    @SuppressLint("UnrememberedMutableState")
    @Test
    fun openSearchWidget_pressCloseButtonOnce_WhenInputIsEmpty_assertClosedState() {

        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)

        composeTestRule.setContent {
            if(searchWidgetShown.value){
                SearchWidget(
                    text = text.value,
                    onTextChange = {
                        text.value = it },
                    onCloseClicked = {
                        searchWidgetShown.value = false
                    },
                    onSearchClicked = {}
                )
            }
        }
        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()

        composeTestRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }


}