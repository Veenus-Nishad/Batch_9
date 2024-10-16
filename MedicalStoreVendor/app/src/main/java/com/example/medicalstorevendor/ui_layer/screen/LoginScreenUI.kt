package com.example.medicalstorevendor.ui_layer.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medicalstorevendor.R
import com.example.medicalstorevendor.ui_layer.AppViewModel
import com.example.medicalstorevendor.ui_layer.Resources.MultiColorText
import com.example.medicalstorevendor.ui_layer.components.AlertDialogBox
import com.example.medicalstorevendor.ui_layer.navigation.HomeScreen
import com.example.medicalstorevendor.ui_layer.navigation.SignUpScreen

@Composable
fun LoginScreenUI(navController: NavController,viewModel: AppViewModel = hiltViewModel()) {
    var showDialog by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(40.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {email=it},
                placeholder = { Text(text = "Enter Your Email") })

            Spacer(modifier = Modifier.height(40.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {password=it},
                placeholder = { Text(text = "Enter Your Password") })

            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = {
                viewModel.login(

                    email = email,
                    password = password
                )
                showDialog = true

            }) {
                Text(text = "Login")
            }

            Spacer(modifier = Modifier.height(40.dp))
            MultiColorText(
                firstString = "Don't have an account?",
                secondString = "Sign Up",
                modifier = Modifier.clickable {
                    navController.navigate(SignUpScreen)

                })

        }
    }
    if (showDialog) {
        AlertDialogBox(
            onDismissRequest = {
                Log.d("AlertDialogBox",  "AlertDialogBox: Dismiss")
                showDialog = false
                //navController.navigate(SignInScreen)
            },
            onConfirmRequest = {
                showDialog = false
                navController.navigate(HomeScreen)
            }
        )
    }
}

