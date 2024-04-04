package com.example.sneakersstore.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.sneakersstore.Data.DetailsUiState
import com.example.sneakersstore.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DetailsViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    fun getProductId(productId: String){
        _uiState.update { currentState ->
            currentState.copy(
                productId = productId
            )
        }
    }

    fun addToCart(product: Product) {
        _uiState.update {currentState ->
            val newCart = if (currentState.cart.indexOf(product) == -1)
                currentState.cart + product
            else currentState.cart

            currentState.copy(
                cart = newCart
            )
        }

        Log.d("cart", _uiState.value.cart.toString())
    }
}