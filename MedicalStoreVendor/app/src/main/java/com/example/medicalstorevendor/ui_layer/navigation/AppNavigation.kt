package com.example.medicalstorevendor.ui_layer.navigation

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medicalstorevendor.ui_layer.screen.HomeScreenUI
import com.example.medicalstorevendor.ui_layer.screen.PlaceOrderScreenUI
import com.example.medicalstorevendor.ui_layer.screen.SignInScreenUI
import com.example.medicalstorevendor.ui_layer.screen.SignUpScreenUI

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = SignInScreen) {

        composable<SignUpScreen> {
            SignUpScreenUI(navController)
        }
        composable<SignInScreen> {
            SignInScreenUI(navController)
        }
        composable<HomeScreen> {
            HomeScreenUI(navController)
        }
        composable<PlaceOrderScreen> {
            PlaceOrderScreenUI()
        }


    }
}

