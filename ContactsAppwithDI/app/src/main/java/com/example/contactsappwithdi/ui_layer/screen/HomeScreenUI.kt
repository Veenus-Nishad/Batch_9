package com.example.contactsappwithdi.ui_layer.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.contactsappwithdi.ui_layer.navigation.AddEditScreen
import com.example.contactsappwithdi.ui_layer.viewModel.ContactAppViewModel

@Composable
fun HomeScreenUI(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ContactAppViewModel = hiltViewModel()
) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate(AddEditScreen) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "null")
        }
    }) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(viewModel.state.value.contactList) {
                Card {
                    Column {
                        Text(text = it.name)
                        Text(text = it.phoneNumber)
                        Text(text = it.email)
                    }
                }
            }
        }
    }
}