package com.example.sneakersstore

import androidx.compose.runtime.mutableStateOf
import com.example.sneakersstore.models.Parts
import com.example.sneakersstore.models.Product
import com.example.sneakersstore.ui.screens.DetailsViewModel
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class DetailsViewModelTest {
    private val viewModel = DetailsViewModel()
    private var product = Product(
        "1",
        R.drawable.nike_pegasus,
        R.string.product_name1,
        R.string.product_desc1,
        89.83,
        mutableStateOf(false),
        mutableStateOf(false),
        listOf(
            Parts(
                "1",
                R.drawable.part1
            )
        )
    )
    @Test
    fun detailsViewModel_setProductId_productIdNotUpdate(){
        var detailUiState = viewModel.uiState.value

        viewModel.setProductId(detailUiState.productId)
        detailUiState = viewModel.uiState.value

        Assert.assertTrue(detailUiState.productId.isEmpty())
    }

    @Test
    fun detailsViewModel_setProductId_productIdUpdate(){
        var productId = "10"

        viewModel.setProductId(productId = productId)
        var detailsUiState = viewModel.uiState.value

        assertEquals(productId, detailsUiState.productId)
    }

    @Test
    fun detailsViewMode_addToCart_updateCart(){
        viewModel.addToCart(product = product)
        var detailsUiState = viewModel.uiState.value

        assertFalse(detailsUiState.cart.isEmpty())
    }
}