package com.example.contactsappwithroomdatabaseversion1.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.contactsappwithroomdatabaseversion1.data.dao.ContactDao
import com.example.contactsappwithroomdatabaseversion1.presentation.routes.SaveEditScreen
import kotlinx.coroutines.launch

@Composable
fun Contacts(dbObject: ContactDao, navController: NavHostController) {

    var customCoroutine= rememberCoroutineScope()

    val sortedContacts = dbObject.getAllContact().groupBy {
        it.name.firstOrNull()?.uppercaseChar() ?: Char.MIN_VALUE
    }

    Scaffold(modifier = Modifier.fillMaxWidth(), floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate(SaveEditScreen) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }) {innerPadding->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            sortedContacts.forEach { (firstLetter, contact) ->
                item { Row(modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                    Text(text = firstLetter.toString()) }
                }
                items(contact,key={it.Id!!}) { contactData ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Text(text = contactData.name)
                        Text(text = contactData.phNo)
                        Text(text = contactData.email)
                        Row {
                            IconButton(onClick = {
                                customCoroutine.launch {
                                    dbObject.deleteContact(contactData)
                                }
                                 }) {
                                Image(imageVector = Icons.Default.Delete, contentDescription = null)
                            }
                            IconButton(onClick = { navController.navigate(SaveEditScreen) }) {
                                Image(imageVector = Icons.Default.Edit, contentDescription = null)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }


            }

        }
    }


}