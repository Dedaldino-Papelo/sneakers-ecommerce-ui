package com.example.sneakersstore

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sneakersstore.ui.screens.HomeScreen

enum class SneakersScreen(@StringRes val title: Int){
    Start(title = R.string.app_name),
    Details(title = R.string.product_details)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SneakersAppBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = "") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = modifier,
        navigationIcon = {
                IconButton(onClick = {  }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = null
                    )
                }
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Localized description"
                )
            }
        },
    )
}

@Composable
fun SneakersApp() {
    val navController = rememberNavController()
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen

    Scaffold(
        topBar = {
            SneakersAppBar()
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = SneakersScreen.Start.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = SneakersScreen.Start.name) {
                HomeScreen()
            }
        }
    }
}
