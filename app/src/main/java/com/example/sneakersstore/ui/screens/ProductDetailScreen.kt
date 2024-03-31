package com.example.sneakersstore.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sneakersstore.ui.theme.SneakersStoreTheme

@Composable
fun ProductDetailScreen(
    detailsViewModel: DetailsViewModel = viewModel(),
    modifier: Modifier = Modifier
){

    val detailsUiState by detailsViewModel.uiState.collectAsState()
    Log.d("newId", detailsUiState.productId)

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
       Column {
           Text(text = "testando...")
       }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailScreenPreview () {
    SneakersStoreTheme {
        ProductDetailScreen()
    }
}