package com.example.contactsapp.ui_layer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.contactsapp.ui_layer.ContactsAppViewModel
import com.example.contactsapp.ui_layer.screens.AddEditScreenUI
import com.example.contactsapp.ui_layer.screens.HomeScreenUI
import com.example.contactsapp.ui_layer.screens.MoreInformationScreenUI
import com.example.contactsapp.ui_layer.screens.RecycleBinUI

@Composable
fun AppNavigation(viewModel: ContactsAppViewModel = hiltViewModel()) {

    val navController = rememberNavController()
    val state = viewModel.state.collectAsState()

    NavHost(navController = navController, startDestination = HomeScreen) {

        composable<HomeScreen> {
            HomeScreenUI(state = state.value, onClickUpsertContact = {
                navController.navigate(AddEditScreen(contactId = null))
            }, onClickRecycleBin = {
                navController.navigate(RecycleBinScreen)
            }, navController = navController)
        }

        composable<AddEditScreen> { backStackEntry ->
            val contactId: AddEditScreen = backStackEntry.toRoute()
            AddEditScreenUI(
                state = state.value,
                contactId = contactId.contactId, //The contactId is passed to determine whether this screen is adding a new contact
                // (if contactId is null) or editing an existing one.
                onClick = { viewModel.upsertContact() },
                onGoToPreviousScreen = { navController.popBackStack() })
        }

        composable<MoreInformationScreen> { backStackEntry ->
            val contactId: MoreInformationScreen = backStackEntry.toRoute()
            MoreInformationScreenUI(
                contactId = contactId.contactId,
                onGoBack = { navController.popBackStack() },
                navController = navController
            )

        }

        composable<RecycleBinScreen> {
            RecycleBinUI(navController = navController, state = state.value)
        }
    }
}