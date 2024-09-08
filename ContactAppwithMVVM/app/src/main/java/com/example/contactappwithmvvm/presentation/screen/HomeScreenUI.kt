package com.example.contactappwithmvvm.presentation.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.contactappwithmvvm.database.tables.Contact
import com.example.contactappwithmvvm.presentation.navigation.AddEditScreen
import com.example.contactappwithmvvm.viewmodel.ContactAppViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HomeScreenUI(
    navController: NavController,
    viewModel: ContactAppViewModel
) {
    // lazyColumn mein dikhane ke liye contacts ko collect kar rahe hai
    val contacts = remember {
        mutableStateOf<List<Contact>>(emptyList<Contact>())
    }

    var expandedDropDownState by remember {
        mutableStateOf(false)
    }

    var expandedCardIndex by remember { mutableStateOf<Int?>(null) }

    val context = LocalContext.current

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
                    IconButton(onClick = { }) {
                        Image(imageVector = Icons.Filled.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { navController.navigate(AddEditScreen) }) {
                        Image(imageVector = Icons.Filled.Add, contentDescription = "Add")
                    }
                    IconButton(onClick = { expandedDropDownState = !expandedDropDownState }) {
                        Image(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "More Options"
                        )
                    }
                    DropdownMenu(
                        expanded = expandedDropDownState,
                        onDismissRequest = { expandedDropDownState = false }) {
                        DropdownMenuItem(
                            text = { Text(text = "Recycle Bin") },
                            onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text(text = "Settings") }, onClick = { /*TODO*/ })
                    }


                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            LazyColumn {
                items(contacts.value) {contact->
                    var isExpanded = expandedCardIndex == contact.id
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxHeight()
                            .clickable { expandedCardIndex = if (isExpanded) null else contact.id }
                    ) {

                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(text = contact.name)
                            if (isExpanded) {
                                Text(text = "Mobile +91 ${contact.number}")
                                Text(text = "Email ${contact.email}")
                                Row {
                                    Icon(imageVector = Icons.Filled.Phone, contentDescription ="Call" )
                                    Icon(imageVector = Icons.Filled.Email, contentDescription ="Email" )
                                    Icon(imageVector = Icons.Filled.Face, contentDescription ="Video Call" )
                                    Icon(imageVector = Icons.Filled.Info, contentDescription ="More Info" )
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}
