package com.example.contactsapp.ui_layer.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.contactsapp.R
import com.example.contactsapp.ui_layer.ContactsAppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreInformationScreenUI(
    contactId:Int,
//    onNavigateToEdit:()->Unit,
      onGoBack:()->Unit
) {
    val viewModel: ContactsAppViewModel = hiltViewModel()
    val contact by viewModel.getContactById(contactId).collectAsState(initial = null)

    Scaffold(topBar = {
        TopAppBar(title = { /*TODO*/ }, navigationIcon = {
            IconButton(onClick = {  }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "backIcon")
            }
        })
    }) { innerPadding ->
        contact?.let {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(Color.LightGray)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Box(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .padding(top = 80.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .fillMaxWidth()
                            .background(color = Color(253,252,255,255))
                            .height(185.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(text = "${it.name}")
                        Text(text = "Phone +91 ${it.phone}}")

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 50.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_call),
                                contentDescription = null, contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .aspectRatio(1f)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.icon_message),
                                contentDescription = null, contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .aspectRatio(1f)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.icon_videocall),
                                contentDescription = null, contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .aspectRatio(1f)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.name_icon),
                            contentDescription = null, contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .aspectRatio(1f)

                        )
                    }

                }

                Spacer(modifier = Modifier.height(15.dp))

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp)
                            .padding(horizontal = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = CenterVertically
                    ) {
                        Text(text = "WhatsApp")
                        Image(
                            painter = painterResource(id = R.drawable.icon_whatsapp),
                            contentDescription = null, contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .aspectRatio(1f)
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        thickness = 2.dp
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp)
                            .padding(horizontal = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = CenterVertically
                    ) {
                        Text(text = "Email")
                        Image(
                            painter = painterResource(id = R.drawable.icon_email),
                            contentDescription = null, contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .aspectRatio(1f)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .fillMaxWidth()
                        .background(Color.White)
                        .height(30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "Favorites",
                        modifier = Modifier.clickable {
                            viewModel.updateFavoriteStatus(contactId, !it.isFavorite!!)
                        }
                    )

                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = "Edit Contact",
                        modifier = Modifier.clickable {

                        })

                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete",
                        modifier = Modifier.clickable {
                            viewModel.updateDeletedStatus(contactId,!it.isDeleted!!)
                            onGoBack()
                        })

                }
            }
        } ?: Text(text = "Contact not found")

    }
}