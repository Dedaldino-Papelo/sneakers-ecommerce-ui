package com.example.sneakersstore.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sneakersstore.R
import com.example.sneakersstore.models.Product
import com.example.sneakersstore.ui.theme.SneakersStoreTheme

@Composable
fun ProductDetailScreen(
    product: Product,
    modifier: Modifier = Modifier
){

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
       Column {
           Text(text = "testando...")
           Text(text = stringResource(id = product.productName))
       }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailScreenPreview () {
    SneakersStoreTheme {
        ProductDetailScreen(
            Product("1",
                R.drawable.nike_pegasus,
                R.string.product_name1,
                R.string.product_desc1,
                7.2)
        )
    }
}