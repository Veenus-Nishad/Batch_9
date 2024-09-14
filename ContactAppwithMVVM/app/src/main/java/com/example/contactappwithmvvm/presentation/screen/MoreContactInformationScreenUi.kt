package com.example.contactappwithmvvm.presentation.screen

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.contactappwithmvvm.database.tables.Contact
import com.example.contactappwithmvvm.viewmodel.ContactAppViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Console


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreContactInformationScreenUI(
    navController: NavController,
    viewModel: ContactAppViewModel,
    id: Int?
) {
    val context = LocalContext.current

    // Activity Result Launcher for sending the SMS
    val smsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        // Handle result if necessary
    }

    Scaffold(topBar = {
        TopAppBar(title = {}, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        })
    }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Image(imageVector = Icons.Filled.Face, contentDescription = null)
            Text(text = "Name")
            Row {
                Text(text = "Mobile")
                Text(text = "+91 ${}")
            }
            Row {
                IconButton(onClick = {
                    val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${id}"))
                    context.startActivity(intent)
                }) {
                    Icon(imageVector = Icons.Default.Phone, contentDescription ="Phone" )
                }
                IconButton(onClick = { val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("smsto:${id}") // Only SMS apps should handle this
                    //putExtra("sms_body", message) // Pre-filled message
                }
                    smsLauncher.launch(intent)  }) {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "Message")

                }
                IconButton(onClick = { viewModel.deleteContact(id!!) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}

