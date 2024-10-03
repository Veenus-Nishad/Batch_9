package com.example.contactsappwithdi.ui_layer.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.contactsappwithdi.ui_layer.navigation.RecycleBinScreen
import com.example.contactsappwithdi.ui_layer.state.ContactState
import com.example.contactsappwithdi.ui_layer.viewModel.ContactAppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecycleBinScreenUI(
    navController: NavController,
    viewModel: ContactAppViewModel = hiltViewModel(),
    contactId: RecycleBinScreen,
    state: ContactState
) {val contact = state.contactList.find { it.id == contactId.contactId }
    Scaffold(topBar = { TopAppBar(title = { Text("Recycle Bin") }, navigationIcon = {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, "backIcon")
        }
    }) }) { innerPadding ->
        LazyColumn(modifier=Modifier.padding(innerPadding)) {

        }
    }
}