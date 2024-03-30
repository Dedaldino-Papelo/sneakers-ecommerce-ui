package com.example.sneakersstore.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sneakersstore.ui.theme.SneakersStoreTheme

@Composable
fun ProductDetailScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
        ){
            items(4) {
                Text(text = it.toString())
            }
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