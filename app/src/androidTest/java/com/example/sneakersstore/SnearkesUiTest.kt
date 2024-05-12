package com.example.sneakersstore

import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.sneakersstore.models.Parts
import com.example.sneakersstore.models.Product
import com.example.sneakersstore.ui.screens.OrderSummaryScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class SneakersUiTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val shoppingCart = listOf(
        Product(
            "1",
            R.drawable.nike_pegasus,
            R.string.product_name1,
            R.string.product_desc1,
            89.83,
            mutableStateOf(false),
            mutableStateOf(false),
            listOf(
                Parts("1", R.drawable.part1)
            )
        ),
        Product(
            "2",
            R.drawable.nike_running_estrada,
            R.string.product_name3,
            R.string.product_desc1,
            70.20,
            mutableStateOf(false),
            mutableStateOf(false),
            listOf(
                Parts("1", R.drawable.part1),
                Parts("2", R.drawable.part2),
                Parts("3", R.drawable.part3)
            )
        )
    )

    @Test
    fun orderSummaryScreen_verifyContentIsDisplayed() {
        composeTestRule.setContent {
            OrderSummaryScreen(
                shoppingCart = shoppingCart,
                onIncreaseQuantity = {},
                onDecreaseQuantity = {}
            )
        }
        shoppingCart.forEach { item ->
            val productName = composeTestRule.activity.getString(item.productName)
            composeTestRule.onNodeWithText(productName).assertIsDisplayed()
        }
    }

    @Test
    fun orderSummaryScreen_clickButton_increaseAndDecreaseQuantity(){
        composeTestRule.setContent {
            OrderSummaryScreen(
                shoppingCart = shoppingCart,
                onIncreaseQuantity = {},
                onDecreaseQuantity = {}
            )
        }
        var quantity = shoppingCart[0].quantity

        composeTestRule.onNodeWithContentDescription("product-1")
            .performClick()
            Assert.assertTrue(quantity == 1)

        composeTestRule.onNodeWithContentDescription("decrease product-1")
            .assertExists()
    }

}