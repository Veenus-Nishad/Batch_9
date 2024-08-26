package com.example.contactsappwithroomdatabaseversion1.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contactsappwithroomdatabaseversion1.data.dao.ContactDao
import com.example.contactsappwithroomdatabaseversion1.presentation.routes.ContactScreen
import com.example.contactsappwithroomdatabaseversion1.presentation.routes.SaveEditScreen
import com.example.contactsappwithroomdatabaseversion1.presentation.screen.AddEditContactScreen
import com.example.contactsappwithroomdatabaseversion1.presentation.screen.Contacts

@Composable
fun App(dbObject: ContactDao,modifier: Modifier=Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ContactScreen) {
        composable<ContactScreen> {
            Contacts(dbObject,navController)
        }
        composable<SaveEditScreen> {
            AddEditContactScreen(navController,dbObject)
        }

    }

}