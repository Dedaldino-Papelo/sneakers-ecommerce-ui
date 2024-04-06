package com.example.sneakersstore.ui.screens

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

    fun setProductId(productId: String){
        _uiState.update { currentState ->
            currentState.copy(
                productId = productId
            )
        }
    }

    fun addToCart(product: Product) {
        _uiState.update {currentState ->
            val updatedCart = _uiState.value.cart.toMutableList()

            val existingProduct  = updatedCart.find { it -> it.productId == product.productId }
            if(existingProduct != null) {
                existingProduct.quantity++
            } else {
                updatedCart.add(product)
            }

            currentState.copy(
                cart = updatedCart
            )
        }
    }

    fun increaseQuantity(item: Product){
        _uiState.update {currentState ->
            val updateCart = _uiState.value.cart.toMutableList()
            val productIndex = updateCart.indexOf(item)

            if(productIndex != -1) {
                val existingProduct = updateCart[productIndex]
                val updatedProduct = existingProduct.copy( quantity = existingProduct.quantity + 1)
                updateCart[productIndex] = updatedProduct
            } else {
                updateCart.add(item.copy( quantity = 1 ))
            }
            currentState.copy(
                cart = updateCart
            )
        }
    }

    fun decreaseQuantity(item: Product){
        _uiState.update { currentState->
            val updateCart = _uiState.value.cart.toMutableList()
            val productIndex = updateCart.indexOf(item)

            if(productIndex != -1){
                val existingProduct = updateCart[productIndex]
                val decreaseQuantity = if(existingProduct.quantity > 1)
                    existingProduct.copy(quantity = existingProduct.quantity - 1)
                else existingProduct.copy(quantity = existingProduct.quantity)

                 updateCart[productIndex] = decreaseQuantity
            }

            currentState.copy(
                cart = updateCart
            )

        }
    }
}