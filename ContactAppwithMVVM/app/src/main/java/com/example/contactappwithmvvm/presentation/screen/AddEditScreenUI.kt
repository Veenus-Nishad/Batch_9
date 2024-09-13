package com.example.contactappwithmvvm.presentation.screen

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.contactappwithmvvm.database.tables.Contact
import com.example.contactappwithmvvm.viewmodel.ContactAppViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AddEditScreenUI(navController: NavController, viewModel: ContactAppViewModel, id: Int?) {

    var name by rememberSaveable {
        mutableStateOf("")
    }
    var number by rememberSaveable {
        mutableStateOf("")
    }
    var email by rememberSaveable {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val customCoroutine = rememberCoroutineScope()



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Name") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            })
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = number,
            onValueChange = { number = it },
            label = { Text(text = "Number") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            modifier = Modifier.width(280.dp),
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )
            },
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            val isContactAlreadyExisting by viewModel.isContactAlreadyExisting(
                name,
                number
            ).collectAsState(initial = false)
            Button(onClick = {
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {


                    if (isContactAlreadyExisting) {
                        Toast.makeText(context, "This name already exist", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        customCoroutine.launch(Dispatchers.IO) {
                            val contact =
                                Contact(name = name, number = number, email = email)
                            viewModel.addUpdateContact(contact)
                        }
                            navController.popBackStack()

                    }
                } else {
                    Toast.makeText(context, "Email is not valid", Toast.LENGTH_LONG).show()
                }


            }) {
                Text(text = "Save")
            }

            Button(onClick = { navController.navigateUp() }) {
                Text(text = "Cancel")
            }
        }

    }
}