package com.example.sneakersstore

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SneakersScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUpSneakersNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            SneakersApp(navController = navController)
        }
    }

    private fun navigateToDetailsScreen(){
        composeTestRule.onNodeWithTag("product_item_1")
            .performClick()
    }

    private fun performNavigateUp(){
        val backTxt = composeTestRule.activity.getString(R.string.Back_button)
        composeTestRule.onNodeWithContentDescription(backTxt)
            .performClick()
    }

    @Test
    fun sneakersNavHost_verifyStartDestination(){
        navController.assertCurrentRouteName(SneakersScreen.Start.name)
    }

     @Test
     fun sneakersNavHost_verifyBackNavigationNotShownOnStartOrderScreen(){
         val backTxt = composeTestRule.activity.getString(R.string.Back_button)
         composeTestRule.onNodeWithContentDescription(backTxt).assertDoesNotExist()
     }

    @Test
    fun sneakersNavHost_clickOneProduct_navigateToDetailsScreen(){
        navigateToDetailsScreen()
        navController.assertCurrentRouteName(SneakersScreen.Details.name)
    }

    @Test
    fun sneakersNavHost_clickBackOnDetailsScreen_navigateToStartOrderScreen(){
        navigateToDetailsScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(SneakersScreen.Start.name)
    }

    @Test
    fun sneakersNavHost_clickMenuButton_navigateToSummaryOrderScreen(){
        val menuText = composeTestRule.activity.getString(R.string.shopping_cart)
        composeTestRule.onNodeWithContentDescription(menuText)
            .performClick()
        navController.assertCurrentRouteName(SneakersScreen.Summary.name)
    }

    @Test
    fun sneakersNavHost_navigateToDetailsScreen_clickMenuButtonAndFail(){
        navigateToDetailsScreen()
        val menuText = composeTestRule.activity.getString(R.string.shopping_cart)
        composeTestRule.onNodeWithContentDescription(menuText)
            .performClick()
        navController.assertCurrentRouteName(SneakersScreen.Summary.name)
    }
}