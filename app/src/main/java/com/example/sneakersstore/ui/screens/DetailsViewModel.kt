package com.example.sneakersstore.ui.screens

import androidx.lifecycle.ViewModel
import com.example.sneakersstore.Data.DetailsUiState
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
}