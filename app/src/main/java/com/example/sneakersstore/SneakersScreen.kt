package com.example.sneakersstore

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sneakersstore.Data.DataSource
import com.example.sneakersstore.ui.screens.DetailsViewModel
import com.example.sneakersstore.ui.screens.HomeScreen
import com.example.sneakersstore.ui.screens.OrderSummaryScreen
import com.example.sneakersstore.ui.screens.ProductDetailScreen

enum class SneakersScreen(@StringRes val title: Int){
    Start(title = R.string.app_name),
    Details(title = R.string.product_details),
    Summary(title = R.string.order_summary)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SneakersAppBar(
    navController: NavHostController,
    currentScreen: SneakersScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = "") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = if(currentScreen.title == R.string.product_details)
                colorResource(R.color.second_color)
            else Color.Transparent
        ),
        modifier = modifier,
        navigationIcon = {
            if(!canNavigateBack) {
                IconButton(onClick = {  }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = null
                    )
                }
            } else {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.Back_button)
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate(SneakersScreen.Summary.name) }) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = stringResource(R.string.shopping_cart)
                )
            }
        }
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SneakersApp(
    viewModel: DetailsViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    SharedTransitionLayout {

        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentScreen = SneakersScreen.valueOf(
            backStackEntry?.destination?.route ?: SneakersScreen.Start.name
        )

        Scaffold(
            topBar = {
                SneakersAppBar(
                    navController = navController,
                    currentScreen = currentScreen,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() }
                )
            }
        ) { innerPadding ->
            val uiState by viewModel.uiState.collectAsState()

            NavHost(
                navController = navController,
                startDestination = SneakersScreen.Start.name,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                composable(route = SneakersScreen.Start.name) {
                    HomeScreen(
                        products = DataSource.products,
                        onNextButtonClicked = {
                            viewModel.setProductId(it.productId)
                            navController.navigate("${SneakersScreen.Details.name}?${it.productId}")
                        },
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this
                    )
                }
                composable("${SneakersScreen.Details.name}") {
                    val product = DataSource.products.find { it -> it.productId == uiState.productId }
                    if (product != null) {
                        ProductDetailScreen(
                            product = product,
                            onAddToCart = { viewModel.addToCart(it) },
                            sharedTransitionScope = this@SharedTransitionLayout,
                            animatedVisibilityScope = this
                        )
                    }
                }

                composable(route = SneakersScreen.Summary.name){
                    OrderSummaryScreen(
                        shoppingCart = uiState.cart,
                        onIncreaseQuantity = { viewModel.increaseQuantity(it) },
                        onDecreaseQuantity = { viewModel.decreaseQuantity(it) }
                    )
                }
            }
        }
    }

}
