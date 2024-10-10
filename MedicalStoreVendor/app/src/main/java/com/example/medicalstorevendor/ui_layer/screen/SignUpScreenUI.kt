package com.example.medicalstorevendor.ui_layer.screen

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
import androidx.navigation.NavHostController
import com.example.medicalstorevendor.R
import com.example.medicalstorevendor.ui_layer.AppViewModel
import com.example.medicalstorevendor.ui_layer.Resources.MultiColorText
import com.example.medicalstorevendor.ui_layer.navigation.SignInScreen
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun SignUpScreenUI(navController: NavController,viewModel:AppViewModel= hiltViewModel()) {

    //val state=viewModel.signUpUserState.value

    var user_name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var pinCode by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
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
                value = user_name,
                onValueChange = {user_name=it},
                placeholder = { Text(text = "Enter Your Name") })

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
            OutlinedTextField(
                value = phoneNumber ,
                onValueChange = {phoneNumber=it},
                placeholder = { Text(text = "Enter Your Phone Number") })

            Spacer(modifier = Modifier.height(40.dp))
            OutlinedTextField(
                value = address,
                onValueChange = {address=it},
                placeholder = { Text(text = "Enter Your Address") })

            Spacer(modifier = Modifier.height(40.dp))
            OutlinedTextField(
                value = pinCode,
                onValueChange = {pinCode=it},
                placeholder = { Text(text = "Enter Your Pin Code") })

            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = {
                viewModel.SignUp(
                    name = user_name,
                    password = password,
                    email = email,
                    phoneNumber = phoneNumber,
                    pinCode=pinCode,
                    address = address,
            )} ){
                Text(text = "Register User")
            }

            Spacer(modifier = Modifier.height(40.dp))
            MultiColorText(
                firstString = "Already have an account?",
                secondString = "Sign In",
                modifier = Modifier.clickable {
                    navController.navigate(SignInScreen)
                })
        }
    }
}