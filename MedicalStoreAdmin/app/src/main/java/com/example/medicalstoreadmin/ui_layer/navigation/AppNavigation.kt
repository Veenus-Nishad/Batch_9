package com.example.medicalstoreadmin.ui_layer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medicalstoreadmin.ui_layer.screens.AddProductScreenUI
import com.example.medicalstoreadmin.ui_layer.screens.HomeScreenUI

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeScreen) {
        composable<HomeScreen> {
            HomeScreenUI(navController = navController)
        }
        composable<AddProductScreen> {
            AddProductScreenUI()
        }
    }
}