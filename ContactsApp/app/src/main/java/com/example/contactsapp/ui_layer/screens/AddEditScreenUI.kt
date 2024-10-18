package com.example.contactsapp.ui_layer.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contactsapp.ui_layer.ContactsAppViewModel
import com.example.contactsapp.ui_layer.navigation.AddEditScreen
import com.example.contactsapp.ui_layer.state.ContactAppState

@Composable
fun AddEditScreenUI(
    state: ContactAppState,
    onEvent: () -> Unit,
) {
    val context= LocalContext.current

}