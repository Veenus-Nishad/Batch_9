package com.example.contactsappwithroomdatabaseversion1.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contactsappwithroomdatabaseversion1.presentation.routes.ContactScreen
import com.example.contactsappwithroomdatabaseversion1.presentation.routes.SaveEditScreen

@Composable
fun App(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ContactScreen) {
        composable<ContactScreen> {}
        composable<SaveEditScreen> {}
    }

}