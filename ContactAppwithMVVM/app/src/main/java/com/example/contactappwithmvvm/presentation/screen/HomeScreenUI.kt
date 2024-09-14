package com.example.contactappwithmvvm.presentation.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.contactappwithmvvm.database.tables.Contact
import com.example.contactappwithmvvm.presentation.navigation.AddEditScreen
import com.example.contactappwithmvvm.presentation.navigation.MoreContactInformationScreen
import com.example.contactappwithmvvm.viewmodel.ContactAppViewModel
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HomeScreenUI(
    navController: NavController, viewModel: ContactAppViewModel
) {
    // lazyColumn mein dikhane ke liye contacts ko collect kar rahe hai
    val contacts = remember {
        mutableStateOf<List<Contact>>(emptyList<Contact>())
    }

    var expandedDropDownState by remember {
        mutableStateOf(false)
    }

    var expandedCardIndex by remember { mutableStateOf<Int?>(null) }

    var getContactById by remember {
        mutableStateOf(null)
    }

    val context = LocalContext.current

    val smsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        // Handle result if necessary
    }
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
    Scaffold(topBar = {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            title = {
                Text(
                    text = "Contacts",
                    fontSize = 24.sp,
                    color = Color(0xFFAA6A8D),
                    fontWeight = FontWeight.Bold
                )
            },
            actions = {
                IconButton(onClick = { }) {
                    Image(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(onClick = { navController.navigate(AddEditScreen(id = -0)) }) {
                    Image(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add",
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(onClick = { expandedDropDownState = !expandedDropDownState }) {
                    Image(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "More Options",
                        modifier = Modifier.size(24.dp)
                    )
                }
                DropdownMenu(expanded = expandedDropDownState,
                    onDismissRequest = { expandedDropDownState = false }) {
                    DropdownMenuItem(text = { Text(text = "Recycle Bin") },
                        onClick = { /*TODO*/ })
                    DropdownMenuItem(text = { Text(text = "Settings") }, onClick = { /*TODO*/ })
                }


            },
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn {
                // Replace with the desired phone number

                items(contacts.value) { contact ->

                    val isExpanded = expandedCardIndex == contact.id
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 8.dp, vertical = 4.dp
                            ) // Add some horizontal padding
                            .clickable { expandedCardIndex = if (isExpanded) null else contact.id },
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White // Slightly lighter than background for subtle contrast
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 0.dp
                        ),
                        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                    ) {

                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp, horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)

                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Face,
                                        contentDescription = "Profile Icon"
                                    )
                                }
                                Text(
                                    text = contact.name,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold, // Adjust font size for readability
                                )
                            }
                            if (isExpanded) {
                                Column(
                                    modifier = Modifier.padding(horizontal = 70.dp)
                                ) {
                                    Text(
                                        text = "Mobile +91 ${contact.number}",
                                        modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,

                                        )
                                    Text(
                                        text = "Email ${contact.email}",
                                        modifier = Modifier.padding(start = 10.dp),
                                        fontSize = 14.sp
                                    )
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 8.dp),
                                        horizontalArrangement = Arrangement.SpaceEvenly,
                                    ) {
                                        Icon(imageVector = Icons.Filled.Phone,
                                            contentDescription = "Call",
                                            modifier = Modifier
                                                .size(48.dp)
                                                .clickable {
                                                    val intent = Intent(
                                                        Intent.ACTION_CALL,
                                                        Uri.parse("tel:${contact.number}")
                                                    )
                                                    context.startActivity(intent)
                                                })
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Icon(imageVector = Icons.Filled.Email,
                                            contentDescription = "Email",
                                            modifier = Modifier
                                                .size(24.dp)
                                                .clickable {
                                                    val intent =
                                                        Intent(Intent.ACTION_SENDTO).apply {
                                                            data =
                                                                Uri.parse("smsto:${contact.number}") // Only SMS apps should handle this

                                                        }
                                                    smsLauncher.launch(intent)
                                                })
                                        Spacer(modifier = Modifier.width(20.dp))
                                        Icon(
                                            imageVector = Icons.Filled.Face,
                                            contentDescription = "Video Call",
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Spacer(modifier = Modifier.width(20.dp))
                                        Icon(imageVector = Icons.Filled.Info,
                                            contentDescription = "More Info",
                                            modifier = Modifier
                                                .size(24.dp)
                                                .clickable {
                                                    navController.navigate(
                                                        MoreContactInformationScreen(
                                                            contact.id
                                                        )
                                                    )
                                                })
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun ShowPreview() {
    HomeScreenUI(
        navController = rememberNavController(),
        viewModel = ContactAppViewModel(context = LocalContext.current)
    )
}