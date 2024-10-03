package com.example.contactsappwithdi.ui_layer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.contactsappwithdi.ui_layer.screen.AddEditScreenUI
import com.example.contactsappwithdi.ui_layer.screen.HomeScreenUI
import com.example.contactsappwithdi.ui_layer.screen.MoreInfoScreenUI
import com.example.contactsappwithdi.ui_layer.screen.RecycleBinScreenUI
import com.example.contactsappwithdi.ui_layer.viewModel.ContactAppViewModel

@Composable
fun  AppNavigation(modifier: Modifier = Modifier, viewModel: ContactAppViewModel = hiltViewModel()) {
        val navController = rememberNavController()
        val state  = viewModel.state.collectAsState()
        NavHost(navController = navController, startDestination = HomeScreen) {
            composable<HomeScreen>{
                HomeScreenUI(navController = navController,state=state.value,viewModel = viewModel)
            }
            composable<AddEditScreen>{backStackEntry ->
                val contactId :AddEditScreen = backStackEntry.toRoute()
                AddEditScreenUI(navController = navController  , state = state.value, contactId = contactId,
                    onEvent = { viewModel.upsertContact() })
            }
            composable<MoreInfoScreen>{backStackEntry ->
                val contactId : MoreInfoScreen = backStackEntry.toRoute()
                MoreInfoScreenUI(navController = navController,contactId = contactId!!,state = state.value)
            }
            composable<RecycleBinScreen> {backStackEntry->
                val contactId : RecycleBinScreen = backStackEntry.toRoute()
                RecycleBinScreenUI(navController = navController,contactId = contactId!!,state = state.value)
            }
        }
}


