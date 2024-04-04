package com.example.sneakersstore.Data

import com.example.sneakersstore.models.Product

data class DetailsUiState(
    val productId: String = "",
    val cart: List<Product> = listOf()
)
