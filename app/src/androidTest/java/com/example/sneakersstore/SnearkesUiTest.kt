package com.example.sneakersstore

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.sneakersstore.ui.theme.SneakersStoreTheme
import org.junit.Rule
import org.junit.Test

class SneakersUiTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun test_addToCart_button_exists(){
        composeTestRule.setContent {
            SneakersStoreTheme {
                SneakersApp()
            }
        }
        val buyNow = composeTestRule.activity.getString(R.string.buy_now)
        composeTestRule.onNodeWithText(buyNow).assertExists()
    }
}