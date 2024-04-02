package com.example.sneakersstore.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Product(
    val productId: String,
    @DrawableRes val productImageRes: Int,
    @StringRes val productName: Int,
    @StringRes val productDesc: Int,
    val productPrice: Double,
    var isFavorite: MutableState<Boolean> = mutableStateOf(false)
)

data class Card(
    val productId: String,
    @DrawableRes val imageRes: Int,
    @StringRes val title: Int,
    @StringRes val description: Int,
)
