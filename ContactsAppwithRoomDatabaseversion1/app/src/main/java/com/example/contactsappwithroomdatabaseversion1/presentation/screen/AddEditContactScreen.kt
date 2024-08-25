package com.example.contactsappwithroomdatabaseversion1.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Preview(showSystemUi = true)
@Composable
fun AddEditContactScreen(modifier: Modifier=Modifier,navController: NavController){
    var name by rememberSaveable {
        mutableStateOf("")
    }
    var phNo by rememberSaveable {
        mutableStateOf("")
    }
    var email by rememberSaveable {
        mutableStateOf("")
    }


    Column(modifier=Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            TextField(value =name , onValueChange ={ name=it }, label = { Text(text = "Name")}, leadingIcon = { Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null
            )} )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(value =phNo , onValueChange ={ phNo=it }, label = { Text(text = "Number")}, leadingIcon = { Icon(
            imageVector = Icons.Default.Phone,
            contentDescription = null
        )} )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(value =email , onValueChange ={ email=it }, label = { Text(text = "Email")}, leadingIcon = { Icon(
            imageVector = Icons.Default.Email,
            contentDescription = null
        )} )
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier=Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Save")
            }

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Cancel")
            }
        }

    }
}