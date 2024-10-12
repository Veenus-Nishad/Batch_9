package com.example.medicalstoreadmin.ui_layer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicalstoreadmin.ui_layer.AppViewModel
import com.example.medicalstoreadmin.ui_layer.navigation.AddProductScreen
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUI(viewModel: AppViewModel = hiltViewModel(), navController: NavController) {
    val response = viewModel.getAllUsersState.collectAsState()
    val data = response.value.Data?.body() ?: emptyList()

    val updateUserAllDetailsState=viewModel.updateUserAllDetailsState.collectAsState()

    when{
        updateUserAllDetailsState.value.Loading->{
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        updateUserAllDetailsState.value.Error!=null->{
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = updateUserAllDetailsState.value.Error.toString())
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "All Users") },
                actions = {
                    IconButton(onClick = { navController.navigate(AddProductScreen) }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            item { }
            items(data!!) { userData ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp, vertical = 10.dp)
                ) {
                    Column {
                        Text(text = userData.user_id)
                        Text(text = userData.name)
                        Text(text = userData.email)
                        Text(text = userData.pinCode)
                    }
                    Row( modifier = Modifier
                        .fillMaxWidth().padding(horizontal = 8.dp, vertical = 5.dp)
                    , horizontalArrangement = Arrangement.SpaceBetween) {
                        Button(onClick = {
                            viewModel.approveUser(
                                user_id=userData.user_id,
                                isApproved = 1
                            )
                        }) {
                            Text(text = "Approve")
                        }
                        Button(onClick = {}) { Text(text = "See All Details") }
                    }
                }
            }
        }
    }

}


