package com.example.sneakersstore.ui.screens

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakersstore.Data.DataSource
import com.example.sneakersstore.R
import com.example.sneakersstore.models.Product
import kotlinx.coroutines.delay

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    products: List<Product>,
    onNextButtonClicked: (Product) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
) {
    var searchText by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp)
        ) {
            SearchBar(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
            )
            Spacer(modifier = Modifier.size(20.dp))
            CardImage()
            Spacer(modifier = Modifier.size(20.dp))
            SectionTitle(
                title = R.string.section_title1,
                link = R.string.section_title2,
                modifier = Modifier
            )
            Brands()
            Spacer(modifier = Modifier.size(20.dp))
            SectionTitle(
                title = R.string.section_title3,
                link = R.string.section_title2,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.size(12.dp))

            Column(modifier = modifier) {
                val chunkedProducts = products.chunked(2)

                chunkedProducts.forEach { products ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        products.forEach { product ->
                            ProductItem(
                                product = product,
                                onClick = { onNextButtonClicked(product) },
                                onCheckedChange = { isFavorite ->
                                    product.isFavorite.value = isFavorite
                                },
                                sharedTransitionScope = sharedTransitionScope,
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ProductItem(
    product: Product,
    onClick: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .width(180.dp)
    ) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(dimensionResource(R.dimen.padding_medium)),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.second_color)
            )
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
            ) {
                IconButton(
                    onClick = { onCheckedChange(!product.isFavorite.value) },
                    modifier = modifier
                        .align(alignment = Alignment.End)
                ) {
                    Icon(
                        if(product.isFavorite.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        tint = if(product.isFavorite.value) Color.Red else Color.Black,
                        contentDescription = null,
                        modifier = modifier
                            .size(25.dp)
                    )
                }

                with(sharedTransitionScope){
                    Image(
                        painter = painterResource(product.productImageRes),
                        contentDescription = null,
                        modifier = modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = "image-${product.productId}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 1000)
                                }
                            )
                            .size(130.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column {
            Text(text = stringResource(id = product.productName))
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = String.format("%.1f", product.productPrice))
        }
    }
}

@Composable
fun SectionTitle(
    @StringRes title:
    Int, @StringRes
    link: Int, modifier: Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.displayMedium
        )
        Text(text = stringResource(link))
    }
}

@Composable
fun Brands(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BrandItem()
        BrandItem()
        BrandItem()
        BrandItem()
    }
}

@Composable
fun BrandItem(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(70.dp)
            .clip(CircleShape)
            .background(colorResource(R.color.second_color))
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = "What are you looking for?") },
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardImage(modifier: Modifier = Modifier) {

    val cards = DataSource.cards
    val pagerState = rememberPagerState(pageCount = {
        cards.size
    })

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.scrollToPage(nextPage)
        }
    }

    Card(
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.black),
        )
    ) {
        HorizontalPager(state = pagerState) { currentPage ->
            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = cards[currentPage].title),
                    color = colorResource(R.color.white),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )
                Text(
                    text = stringResource(id = cards[currentPage].description),
                    lineHeight = 20.sp,
                    color = colorResource(R.color.gray),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .width(120.dp)
                )
                Button(
                    onClick = { },
                    modifier = Modifier
                        .padding(top = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.button_color)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.text_shop_button),
                        color = colorResource(R.color.white)
                    )
                }
            }
            Image(
                modifier = Modifier
                    .size(200.dp),
                painter = painterResource(id = cards[currentPage].imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) colorResource(R.color.button_color) else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(7.dp)
            )
        }
    }
}


/*@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true)
@Composable
fun SharedTransitionScope.HomeScreenPreview() {
    SneakersStoreTheme {
        HomeScreen(
            onNextButtonClicked = {},
            products = DataSource.products,
            sharedTransitionScope = this,
            animatedVisibilityScope =
        )
    }
}*/
