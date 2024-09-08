package com.example.contactappwithmvvm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contactappwithmvvm.presentation.screen.AddEditScreenUI
import com.example.contactappwithmvvm.presentation.screen.HomeScreenUI

@Composable
fun AppNavigation(modifier: Modifier=Modifier){
    val navController=rememberNavController()
    NavHost(navController = navController, startDestination = HomeScreen) {
        composable<HomeScreen>{
            HomeScreenUI(navController)
        }
        composable<AddEditScreen>{
            AddEditScreenUI(navController)
        }

    }
}

