package com.example.contactappwithmvvm.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun HomeScreenUI(navController: NavController = rememberNavController()) {
    Scaffold( // ACTION FIELD ME VO RIGHT SIDE AATEIN  HAI APP BAR KE ARRAY BHI PASS KAR SAKTE HAIN
        topBar = {
            TopAppBar(
                title = { Text(text = "Contacts") },
                actions = {

                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {

        }
    }
}
