package com.example.contactsappwithdi.ui_layer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contactsappwithdi.ui_layer.screen.AddEditScreenUI
import com.example.contactsappwithdi.ui_layer.screen.HomeScreenUI
import com.example.contactsappwithdi.ui_layer.viewModel.ContactAppViewModel

@Composable
fun  AppNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = HomeScreen) {
            composable<HomeScreen>{
                HomeScreenUI(navController = navController)
            }
            composable<AddEditScreen>{
                val addEditScreen =  { AddEditScreen() }
                AddEditScreenUI(navController)
            }
        }
}


