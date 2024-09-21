package com.example.contactsappwithdi.ui_layer.screen

import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.contactsappwithdi.R
import com.example.contactsappwithdi.ui_layer.state.ContactState
import com.example.contactsappwithdi.ui_layer.viewModel.ContactAppViewModel

@Composable
fun AddEditScreenUI(
    navController: NavController,
    viewModel: ContactAppViewModel = hiltViewModel(),
    state: ContactState,
    onEvent: () -> Unit,
    contactId: Int?
) {
    val context = LocalContext.current
    // Find the contact by ID, if present

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            // iska ka jo return ya result hai vo milta hai ek uri
            if (it != null) {
                val inputStream =
                    context.contentResolver.openInputStream(it) // is code se image ko read karke laare hai
                val bytes = inputStream?.readBytes() // reading the Bytes of the image
                state.image.value = bytes
            }
        }
    val contact = contactId?.let { id ->
        state.contactList.find { it.id == id }
    }
    contact?.let {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(22.dp))
            if (state.image.value == null) {
                Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "null",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .aspectRatio(1f)
                        .clickable {
                            launcher.launch("image/*") // jab sirf image read karni ho
                        })
            } else {
                Image(
                    bitmap = BitmapFactory.decodeByteArray(
                        state.image.value,
                        0,
                        state.image.value!!.size
                    ).asImageBitmap(), contentDescription = null,
                    modifier = Modifier.clickable {
                        launcher.launch("image/*") // jab sirf image read karni ho
                    }
                )
            }
            Spacer(modifier = Modifier.height(22.dp))
            OutlinedTextField(
                value = state.name.value,
                onValueChange = { state.name.value = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Name"
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null
                    )
                },
                shape = RoundedCornerShape(18.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = state.phoneNumber.value,
                onValueChange = { state.phoneNumber.value = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Phone"
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Phone,
                        contentDescription = null
                    )
                },
                shape = RoundedCornerShape(18.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = state.email.value,
                onValueChange = { state.email.value = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Email"
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = null
                    )
                },
                shape = RoundedCornerShape(18.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = state.email.value,
                onValueChange = {state.email.value=it},
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "DOB"
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = null
                    )
                },
                shape = RoundedCornerShape(18.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = { navController.navigateUp() }) {
                    Text(text = "Cancel")
                }
                Button(onClick = {
                    onEvent.invoke()
                    navController.navigateUp()
                }) {
                    Text(text = "Save")
                }
            }
        }
    } ?: Text(text = "Editing new")


}