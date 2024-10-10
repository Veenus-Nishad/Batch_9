package com.example.medicalstorevendor.ui_layer.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun AlertDialogBox(
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit,
) {
    AlertDialog(
        onDismissRequest =  onDismissRequest,
        confirmButton = {
            Button(onClick = onConfirmRequest) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(onClick =  onDismissRequest  ) {
                    Text(text = "Cancel")
            }
        },
        icon = {
            Icon(imageVector = Icons.Default.Info, contentDescription = "Alert Icon")
        },
        title = {
            Text(text = "UnVerified Account")},
        text = {
            Text(text = "Your Account is not Verified by the Admin Do you Still wish to Continue.")
        }
    )
}