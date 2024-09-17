package com.example.contactsappwithdi.ui_layer.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.contactsappwithdi.ui_layer.viewModel.ContactAppViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AddEditScreenUI(
    navController: NavController,
    viewModel: ContactAppViewModel = hiltViewModel(),
    id: Int? = null
) {
    Column {
        OutlinedTextField(
            value = viewModel.state.value.name.value,
            onValueChange = { viewModel.state.value.name.value = it }
        )
        OutlinedTextField(
            value = viewModel.state.value.phoneNumber.value,
            onValueChange = { viewModel.state.value.phoneNumber.value = it }
        )
        OutlinedTextField(
            value = viewModel.state.value.email.value,
            onValueChange = { viewModel.state.value.email.value = it }
        )
        Button(onClick = {
            viewModel.upsertContact()
            navController.navigateUp()
        }) {
            Text(text = "Save")
        }
    }
}