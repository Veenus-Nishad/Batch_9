package com.example.contactsapp.ui_layer.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
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
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.contactsapp.R
import com.example.contactsapp.ui_layer.state.ContactAppState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecycleBinUI(
    navController: NavController,
    state: ContactAppState
) {
    val deletedContacts = state.contactList.filter { it.isDeleted == true }
        .groupBy { it.name.firstOrNull()?.uppercaseChar() ?: Char.MIN_VALUE }.toSortedMap()

    var expandedCardIndex by remember { mutableStateOf<Int?>(null) }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Recycle Bin") },
        )
    }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            deletedContacts.forEach { (firstLetter, contact) ->
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) { Text(text = firstLetter.toString()) }
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
                                Row(horizontalArrangement = SpaceBetween) {
                                    IconButton(onClick = {
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Restore,
                                            contentDescription = "Restore"
                                        )
                                        IconButton(onClick = {

                                        }) {
                                            Icon(
                                                imageVector = Icons.Default.DeleteForever,
                                                contentDescription = "Restore"
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
    }
}