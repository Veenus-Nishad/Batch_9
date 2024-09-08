package com.example.contactappwithmvvm.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.contactappwithmvvm.database.tables.Contact
import com.example.contactappwithmvvm.presentation.navigation.AddEditScreen
import com.example.contactappwithmvvm.viewmodel.ContactAppViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HomeScreenUI(
    navController: NavController ,
    viewModel: ContactAppViewModel
) {
    // lazyColumn mein dikhane ke liye contacts ko collect kar rahe hai
    val contacts = remember{
        mutableStateOf<List<Contact>>(emptyList<Contact>())
    }

    val colorBrush = Brush.linearGradient(listOf(Color.Blue, Color.Yellow))

    // jetpack Compose mein composable ka bhi ek coroutine hota hai
    // launched effect tab chalta hai jab first time vo coroutine compose hota hai
    // so when you want a certain task to be done on first time composition of the composable or function then you use this
    /*
        .collect{} -> collects the changes sequentially and updates the UI and repeat from start for every change
        .collectLatest{} -> collects the latest changes and update UI
    */
    LaunchedEffect(
        key1 = viewModel.db.contactDao().getAllContacts()
    ) { // unit,false ya true likhne se launch effect ek hi baar chalega
        // varna key = tumhara change
        viewModel.db.contactDao().getAllContacts().collectLatest {
            contacts.value = it
        }
    }

    // ACTION FIELD ME VO RIGHT SIDE AATEIN  HAI APP BAR KE ARRAY BHI PASS KAR SAKTE HAIN
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Contacts") },
                actions = {
                    IconButton(onClick = {  }) {
                        Image(imageVector = Icons.Filled.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { navController.navigate(AddEditScreen)  }) {
                        Image(imageVector = Icons.Filled.Add, contentDescription = "Add")
                    }
                    IconButton(onClick = { navController.navigate(AddEditScreen)  }) {
                        Image(imageVector = Icons.Filled.MoreVert, contentDescription = "More Options")
                    }


                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            LazyColumn {
                items(contacts.value) {
                    Card(
                        modifier = Modifier.padding(10.dp)
                            .height(30.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(text = it.name)
                            Text(text = it.number)
                            Text(text = it.email)
                        }
                    }
                }
            }

        }
    }
}
