package com.example.medicalstoreadmin.ui_layer.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medicalstoreadmin.ui_layer.AppViewModel
import retrofit2.Response


@Composable
fun HomeScreenUI(viewModel: AppViewModel = hiltViewModel()) {
    val response = viewModel.getAllUsersState.collectAsState()
    val data = response.value.Data?.body()?: emptyList()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {  }
        items(data!!){userData->
            Card {
                Column {
                    Text(text = userData.user_id)
                    Text(text = userData.name)
                    Text(text = userData.email)
                    Text(text = userData.pinCode)
                }
                Row {
                    Button(onClick = {

                    }) {
                        Text(text="Approve")
                    }
                    Button(onClick = {}) { Text(text="See All Details") }
                }
            }
        }
    }
}


