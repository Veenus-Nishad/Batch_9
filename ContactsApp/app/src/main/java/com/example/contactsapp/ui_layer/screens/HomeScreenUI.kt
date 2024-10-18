package com.example.contactsapp.ui_layer.screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.example.contactsapp.R
import com.example.contactsapp.ui_layer.state.ContactAppState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUI(
    navController: NavController,
    state: ContactAppState,
){
    val context=LocalContext.current
    val nonDeletedContacts = state.contactList
        .filter { contact -> contact.isDeleted == false }  // explicitly check for false since isDeleted is nullable
        .groupBy { contact ->
            contact.name.firstOrNull()?.uppercaseChar() ?: Char.MIN_VALUE
        }
        .toSortedMap()
    var expandedCardIndex by remember { mutableStateOf<Int?>(null) }
    val favoriteContacts = state.contactList.filter { it.isFavorite!! }
    var expandedCardIndexFav by remember { mutableStateOf<Int?>(null) }

    //sms
    val smsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
    }

    Scaffold(topBar = {TopAppBar(
        title = { Text(text = "Contacts") },
        actions={
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Contact",modifier=Modifier.clickable {})
            Icon(imageVector = Icons.Default.Recycling, contentDescription = "Recycle Bin",modifier=Modifier.clickable {})
        }

    )}) { innerPadding->
        LazyColumn( modifier = Modifier
            .padding(innerPadding).fillMaxSize()){
            item {
                Text(
                    "Favorite Contacts",
                    modifier = Modifier.padding(16.dp)
                )
            }
            items(favoriteContacts) { favContact ->
                val isExpanded = expandedCardIndexFav == favContact.id
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 8.dp
                        )
                        .clickable {
                            expandedCardIndexFav = if (isExpanded) null else favContact.id
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color(
                            253,
                            252,
                            255,
                            255
                        ) // Slightly lighter than background for subtle contrast
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
                            Text(text = favContact.name)
                        }
                        if (isExpanded) {
                            Column {
                                Text(
                                    text = "Phone +91${favContact.phone}",
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
                                        painter = painterResource(id = R.drawable.icon_call),
                                        contentDescription = "Call",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(36.dp)
                                            .clip(CircleShape)
                                            .aspectRatio(1f)
                                            .clickable {
                                                val intent = Intent(
                                                    Intent.ACTION_CALL,
                                                    Uri.parse("tel:${favContact.phone}")
                                                )
                                                context.startActivity(intent)
                                            }
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.icon_message),
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
                                                            Uri.parse("smsto:${favContact.phone}") // Only SMS apps should handle this

                                                    }
                                                smsLauncher.launch(intent)
                                            }
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.icon_videocall),
                                        contentDescription = "Video Call",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(36.dp)
                                            .clip(CircleShape)
                                            .aspectRatio(1f)
                                            .clickable {
                                                val intent = Intent(
                                                    Intent.ACTION_CALL,
                                                    Uri.parse("tel:${favContact.phone}")
                                                )
                                                context.startActivity(intent)
                                            }
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.icon_moreinfo),
                                        contentDescription = "More Info",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(36.dp)
                                            .clip(CircleShape)
                                            .aspectRatio(1f)
                                            .clickable {
                                                //navController.navigate(MoreInfoScreen(favContact.id!!))
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
                    }
                }
            }
            nonDeletedContacts.forEach { (firstLetter, contact) ->
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = firstLetter.toString())
                    }
                }


                items(contact, key = { it.id!! }) { contactData ->
                    val isExpanded = expandedCardIndex == contactData.id
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 8.dp
                            )
                            .clickable {
                                expandedCardIndex = if (isExpanded) null else contactData.id
                            },
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
                                Text(text = contactData.name)
                            }
                            if (isExpanded) {
                                Column {
                                    Text(
                                        text = "Phone +91${contactData.phone}",
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
                                            painter = painterResource(id = R.drawable.icon_call),
                                            contentDescription = "Call",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(36.dp)
                                                .clip(CircleShape)
                                                .aspectRatio(1f)
                                                .clickable {
                                                    val intent = Intent(
                                                        Intent.ACTION_CALL,
                                                        Uri.parse("tel:${contactData.phone}")
                                                    )
                                                    context.startActivity(intent)
                                                }
                                        )
                                        Image(
                                            painter = painterResource(id = R.drawable.icon_message),
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
                                                                Uri.parse("smsto:${contactData.phone}") // Only SMS apps should handle this

                                                        }
                                                    smsLauncher.launch(intent)
                                                }
                                        )
                                        Image(
                                            painter = painterResource(id = R.drawable.icon_videocall),
                                            contentDescription = "Video Call",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(36.dp)
                                                .clip(CircleShape)
                                                .aspectRatio(1f)
                                                .clickable {
                                                    val intent = Intent(
                                                        Intent.ACTION_CALL,
                                                        Uri.parse("tel:${contactData.phone}")
                                                    )
                                                    context.startActivity(intent)
                                                }
                                        )
                                        Image(
                                            painter = painterResource(id = R.drawable.icon_moreinfo),
                                            contentDescription = "More Info",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(36.dp)
                                                .clip(CircleShape)
                                                .aspectRatio(1f)
                                                .clickable {
//                                                    navController.navigate(
//                                                        MoreInfoScreen(
//                                                            contactData.id!!
//                                                        )
//                                                    )
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
                        }
                    }
                }
            }
        }
    }
}