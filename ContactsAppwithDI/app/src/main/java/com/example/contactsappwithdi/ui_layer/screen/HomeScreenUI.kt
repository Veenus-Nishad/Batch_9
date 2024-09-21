package com.example.contactsappwithdi.ui_layer.screen

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.contactsappwithdi.R
import com.example.contactsappwithdi.ui_layer.navigation.AddEditScreen
import com.example.contactsappwithdi.ui_layer.navigation.MoreInfoScreen
import com.example.contactsappwithdi.ui_layer.state.ContactState
import com.example.contactsappwithdi.ui_layer.viewModel.ContactAppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUI(
    navController: NavController,
    viewModel: ContactAppViewModel = hiltViewModel(),
    state: ContactState
) {

    val context = LocalContext.current

    var expandedCardIndex by remember { mutableStateOf<Int?>(null) }

    val smsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        // Handle result if necessary
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Contact") },
            actions = { Icon(imageVector = Icons.Default.Add, contentDescription = "null") })
    }, floatingActionButton = {
        FloatingActionButton(onClick = {
            navController.navigate(AddEditScreen)
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "null")
        }
    }) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(state.contactList) { contact ->
                val isExpanded = expandedCardIndex == contact.id
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 8.dp
                        )
                        .clickable { expandedCardIndex = if (isExpanded) null else contact.id },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White // Slightly lighter than background for subtle contrast
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 0.dp
                    )
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                .height(52.dp)
                                .padding(10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.name_icon),
                                contentDescription = "Profile Icon",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .aspectRatio(1f)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(text = contact.name)
                        }
                        if (isExpanded) {
                            Column {
                                Text(
                                    text = "Mobile 91${contact.phoneNumber}",
                                    modifier = Modifier.padding(horizontal = 54.dp)
                                )
                                Spacer(modifier = Modifier.height(22.dp))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 48.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.name_icon),
                                        contentDescription = "Call",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(36.dp)
                                            .clip(CircleShape)
                                            .aspectRatio(1f)
                                            .clickable {
                                                val intent = Intent(
                                                    Intent.ACTION_CALL,
                                                    Uri.parse("tel:${contact.phoneNumber}")
                                                )
                                                context.startActivity(intent)
                                            }
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.name_icon),
                                        contentDescription = "Message",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(36.dp)
                                            .clip(CircleShape)
                                            .aspectRatio(1f)
                                            .clickable {
                                                val intent =
                                                    Intent(Intent.ACTION_SENDTO).apply {
                                                        data =
                                                            Uri.parse("smsto:${contact.phoneNumber}") // Only SMS apps should handle this

                                                    }
                                                smsLauncher.launch(intent)
                                            }
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.name_icon),
                                        contentDescription = "Video Call",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(36.dp)
                                            .clip(CircleShape)
                                            .aspectRatio(1f)
                                            .clickable {
                                                val intent = Intent(
                                                    Intent.ACTION_CALL,
                                                    Uri.parse("tel:${contact.phoneNumber}")
                                                )
                                                context.startActivity(intent)
                                            }
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.name_icon),
                                        contentDescription = "More Info",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(36.dp)
                                            .clip(CircleShape)
                                            .aspectRatio(1f)
                                            .clickable {
                                                navController.navigate(MoreInfoScreen(contact.id!!))
                                            }
                                    )
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                HorizontalDivider(
                                    modifier = Modifier.padding(horizontal = 48.dp),
                                    thickness = 2.dp
                                )
                            }
                        }

//                        Icon(
//                            imageVector = Icons.Default.Delete,
//                            contentDescription = null,
//                            modifier = Modifier.clickable {
//                                viewModel.state.value.name.value = it.name
//                                viewModel.state.value.phoneNumber.value = it.phoneNumber
//                                viewModel.state.value.email.value = it.email
//                                viewModel.state.value.id.value = it.id
//                                viewModel.state.value.dob.value = it.DOB
//                                viewModel.state.value.image.value = it.image
//                                viewModel.deleteContact()
//                            })


                    }
                }
            }
        }
    }
}
