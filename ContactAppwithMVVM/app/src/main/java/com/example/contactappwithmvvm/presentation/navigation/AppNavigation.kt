package com.example.contactappwithmvvm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.contactappwithmvvm.database.tables.Contact
import com.example.contactappwithmvvm.presentation.screen.AddEditScreenUI
import com.example.contactappwithmvvm.presentation.screen.DeletedContactsScreenUI
import com.example.contactappwithmvvm.presentation.screen.HomeScreenUI
import com.example.contactappwithmvvm.presentation.screen.MoreContactInformationScreenUI
import com.example.contactappwithmvvm.viewmodel.ContactAppViewModel

@Composable
fun AppNavigation(modifier: Modifier=Modifier,viewModel: ContactAppViewModel){
    val navController=rememberNavController()
    NavHost(navController = navController, startDestination = HomeScreen) {
        composable<HomeScreen>{
            HomeScreenUI(navController,viewModel)
        }
        composable<AddEditScreen>{
            val addEditScreen=it.toRoute<AddEditScreen>() // ye id lene ke liye
            AddEditScreenUI(navController,viewModel,addEditScreen.id)
        }
        composable<DeletedContactsScreen> {
            DeletedContactsScreenUI(navController,viewModel)
        }
        composable<MoreContactInformationScreen> {
            val moreContactInformationScreen=it.toRoute<MoreContactInformationScreen>()
            MoreContactInformationScreenUI(navController,viewModel,moreContactInformationScreen.id)
        }

    }
}
