package com.example.contactsapp.ui_layer.screens

import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.contactsapp.R
import com.example.contactsapp.ui_layer.ContactsAppViewModel
import com.example.contactsapp.ui_layer.state.ContactAppState

@Composable
fun AddEditScreenUI(
    state: ContactAppState,
    onClick : ()-> Unit,
    contactId:Int?,
    onGoToPreviousScreen:()->Unit,
    viewModel: ContactsAppViewModel= hiltViewModel(),
) {
    val context= LocalContext.current
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

    val contact = if (contactId != null) {
        state.contactList.find { it.id == contactId }
    } else {
        null
    }


    // Reset the form if contactId is null (i.e., adding a new contact)
    LaunchedEffect(contactId) {
        if (contactId == null) {
            state.apply {
                id.value = null
                name.value = ""
                phoneNumber.value = ""
                email.value = ""
                image.value = null
                isFavorite.value = false
                isDeleted.value = false
            }
        } else {
            contact?.let {
                state.apply {
                    id.value = it.id
                    name.value = it.name
                    phoneNumber.value = it.phone
                    email.value = it.email
                    image.value = it.image
                    isFavorite.value = it.isFavorite ?: false
                    isDeleted.value = it.isDeleted ?: false
                }
            }
        }
    }


    Column(modifier= Modifier.background(color = Color(0xFFf6f6f8)), horizontalAlignment = Alignment.CenterHorizontally) {
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
            Button(onClick = {onGoToPreviousScreen() }) {
                Text(text = "Cancel")
            }
            Button(onClick = {
                onClick()
                onGoToPreviousScreen()
            }) {
                Text(text = "Save")
            }
        }
    }
}