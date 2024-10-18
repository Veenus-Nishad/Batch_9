package com.example.contactsapp.ui_layer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.contactsapp.ui_layer.ContactsAppViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import com.example.contactsapp.ui_layer.screens.AddEditScreenUI
import com.example.contactsapp.ui_layer.screens.HomeScreenUI

@Composable
fun AppNavigation( viewModel: ContactsAppViewModel=hiltViewModel()) {
    val navController = rememberNavController()
    val state  = viewModel.state.collectAsState()
    NavHost(navController = navController, startDestination = HomeScreen) {
        composable<HomeScreen>{
            HomeScreenUI(navController = navController,state=state.value)
        }
        composable<AddEditScreen>{
            AddEditScreenUI(state=state.value)
        }
    }
}