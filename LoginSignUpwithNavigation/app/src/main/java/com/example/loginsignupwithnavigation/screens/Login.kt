package com.example.loginsignupwithnavigation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.loginsignupwithnavigation.navigation.LoginScreen
import com.example.loginsignupwithnavigation.navigation.SignUpScreen


@Composable
fun Login(navController: NavHostController) {
    var emailInput by rememberSaveable {
        mutableStateOf("")
    }
    var passwordInput by rememberSaveable {
        mutableStateOf("")
    }
    val (textColor, setTextColor) = remember { mutableStateOf(Color.Blue) }
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Login", modifier = Modifier.padding(top = 168.dp), fontSize = 34.sp)
        TextField(value = emailInput, onValueChange = {
            emailInput = it
        }, placeholder = { Text("Enter Your Email") }, leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = null
            )
        })
        TextField(value = passwordInput, onValueChange = {
            passwordInput = it
        }, placeholder = { Text("Enter Your Password") }, leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Create,
                contentDescription = null
            )
        })
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Login")
        }
        Row() {
            Text(text = "Don't have an Account? ")
            Text(text = "SignUp",
                color = textColor,
                modifier = Modifier.clickable {
                    setTextColor(if (textColor == Color.Blue) Red else Color.Blue)
                    navController.navigate(SignUpScreen)
                })
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ShowPreview(){

}
