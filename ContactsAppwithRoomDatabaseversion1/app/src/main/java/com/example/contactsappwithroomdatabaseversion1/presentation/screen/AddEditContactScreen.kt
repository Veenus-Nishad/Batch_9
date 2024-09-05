package com.example.contactsappwithroomdatabaseversion1.presentation.screen

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.navigation.NavHostController
import com.example.contactsappwithroomdatabaseversion1.data.dao.ContactDao
import com.example.contactsappwithroomdatabaseversion1.data.tables.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun AddEditContactScreen(
    navController: NavHostController,
    dbObject: ContactDao
) {
    var name by rememberSaveable {
        mutableStateOf("")
    }
    var phNo by rememberSaveable {
        mutableStateOf("")
    }
    var email by rememberSaveable {
        mutableStateOf("")
    }
    val context = LocalContext.current

    val customCoroutine= rememberCoroutineScope()


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
            value = phNo,
            onValueChange = { phNo = it },
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
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )
            })
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                  /* Global Scope Wali Approach which is bakwass
                    GlobalScope.launch(Dispatchers.IO) {
                        if (dbObject.isContactAlreadyExisting(name = name, number = phNo)
                                .isNotEmpty()
                        ) {
                            Toast.makeText(context, "This name already exist", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            dbObject.saveUpdateContact(
                                Contact(name = name, phNo = phNo, email = email)
                            )
                            navController.navigateUp()
                        }
                    } */
                    // Custom Coroutine Scope  Approach
                    customCoroutine.launch(Dispatchers.IO) {
                        if (dbObject.isContactAlreadyExisting(name = name, number = phNo)
                                .isNotEmpty()
                        ) {
                            Toast.makeText(context, "This name already exist", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            dbObject.saveUpdateContact(
                                Contact(name = name, phNo = phNo, email = email)
                            )
                            navController.navigateUp()
                        }
                    }
                } else {
                    //Toast humari app nahi android show karti hai isiliye context pass karte hai
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