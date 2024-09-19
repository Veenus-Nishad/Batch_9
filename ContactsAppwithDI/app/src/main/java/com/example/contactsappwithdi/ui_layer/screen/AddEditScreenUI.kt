package com.example.contactsappwithdi.ui_layer.screen

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.Outline
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.contactsappwithdi.R
import com.example.contactsappwithdi.ui_layer.viewModel.ContactAppViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AddEditScreenUI(
    navController: NavController,
    viewModel: ContactAppViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state = viewModel.state.collectAsState()
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            // iska ka jo return ya result hai vo milta hai ek uri
            if (it != null) {
                val inputStream =
                    context.contentResolver.openInputStream(it) // is code se image ko read karke laare hai
                val bytes = inputStream?.readBytes() // reading the Bytes of the image
                state.value.image.value = bytes
            }
        }
    Column {
        if (state.value.image.value == null) {
            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "null",
                modifier = Modifier.clickable {
                    launcher.launch("image/*") // jab sirf image read karni ho
                })
        } else {
            Image(
                bitmap = BitmapFactory.decodeByteArray(
                    state.value.image.value,
                    0,
                    state.value.image.value!!.size
                ).asImageBitmap(), contentDescription = null,
                modifier = Modifier.clickable {
                    launcher.launch("image/*") // jab sirf image read karni ho
                }
            )
        }

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

@Preview(showSystemUi = true)
@Composable
fun Add() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(22.dp))
        Image(
            painter = painterResource(id = R.drawable.name_icon),
            contentDescription = null, contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(22.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
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
            value = "",
            onValueChange = {},
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
            value = "",
            onValueChange = {},
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
            value = "",
            onValueChange = {},
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
        Row(modifier=Modifier.fillMaxWidth()
            ,horizontalArrangement = Arrangement.SpaceAround){
            Button( onClick = { /*TODO*/ }) {
                Text(text = "Cancel")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Save")
            }
        }
    }
}