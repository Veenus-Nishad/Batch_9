package com.example.contactsappwithroomdatabaseversion1.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.contactsappwithroomdatabaseversion1.data.dao.ContactDao
import com.example.contactsappwithroomdatabaseversion1.presentation.routes.SaveEditScreen

@Composable
fun Contacts( dbObject: ContactDao, navController: NavHostController){

    Scaffold(modifier= Modifier.fillMaxWidth(), floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate(SaveEditScreen) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }) {
            LazyColumn(modifier=Modifier.padding(it)) {
            items(dbObject.getAllContact()){
                Card(modifier=Modifier.fillMaxWidth()) {
                    Text(text = it.name)
                    Text(text = it.phNo)
                    Text(text = it.email)
                    IconButton(onClick = { dbObject.deleteContact(it) }) {
                        Image(imageVector = Icons.Default.Delete, contentDescription = null )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
            }
    }


}