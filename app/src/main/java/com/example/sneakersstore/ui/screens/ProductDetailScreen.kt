package com.example.sneakersstore.ui.screens

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sneakersstore.R
import com.example.sneakersstore.models.Parts
import com.example.sneakersstore.models.Product
import com.example.sneakersstore.ui.components.CustomButton
import java.text.NumberFormat

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ProductDetailScreen(
    product: Product,
    onAddToCart: (Product) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    var items by rememberSaveable { mutableStateOf(listOf<Parts>()) }
    var imageResource: Int = product.productImageRes

    if (items.isNotEmpty()) {
        items.forEach { item ->
            imageResource = when (item.id) {
                "1" -> item.image
                "2" -> item.image
                "3" -> item.image
                else -> product.productImageRes
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(380.dp)
                .background(
                    colorResource(R.color.second_color),
                    shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            with(sharedTransitionScope){
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = stringResource(id = product.productName),
                    modifier = modifier
                        .sharedElement(
                            state = rememberSharedContentState(key = "image-${product.productId}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        )
                )
            }
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                LazyRow(
                    contentPadding = PaddingValues(vertical = 25.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(product.parts) { part ->
                        PartItem(
                            part = part,
                            imageResource = imageResource,
                            onClick = { item ->
                                items = listOf(item)
                            }
                        )
                    }
                }

                Column(
                    modifier = modifier
                ) {
                    Text(
                        text = stringResource(id = product.productDesc),
                        color = colorResource(R.color.gray),
                        modifier = modifier.padding(bottom = 12.dp)
                    )
                    Text(
                        text = stringResource(id = product.productName),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = modifier.padding(bottom = 12.dp)
                    )
                    Text(
                        text = NumberFormat.getCurrencyInstance().format(product.productPrice),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

           CustomButton(
               onClick = { onAddToCart(product) },
               label = R.string.buy_now,
               modifier = Modifier
                   .padding(bottom = 20.dp)
                   .fillMaxWidth()
           )
        }
    }
}

@Composable
fun PartItem(
    part: Parts,
    onClick: (Parts) -> Unit,
    @StringRes imageResource: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(colorResource(R.color.light_gray))
            .width(60.dp)
            .height(60.dp)
            .border(
                2.dp,
                color = if (imageResource == part.image) colorResource(R.color.button_color) else Color.Transparent,
            )
            .clickable {
                onClick(part)
            },
    ) {
        Image(
            painter = painterResource(part.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .align(alignment = Alignment.Center)
        )
    }
}


/*@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun ProductDetailScreenPreview () {
    SneakersStoreTheme {
        ProductDetailScreen(
            Product(
                "1",
                R.drawable.nike_pegasus,
                R.string.product_name1,
                R.string.product_desc1,
                89.83,
                mutableStateOf(false),
                listOf()
            ),
            onAddToCart = {}
        )
    }
}*/
