package com.example.shoppingappcustomer.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shoppingappcustomer.domain.models.UserData

@Composable
fun SignUpScreen(
    viewModel: ViewModel = hiltViewModel()
) {
    val registerState=viewModel.registerUserState.collectAsState()

    val localContext= LocalContext.current

    when{
        registerState.value.isLoading->{
            CircularProgressIndicator()
        }
        registerState.value.data?.isNotEmpty() == true ->{
            Toast.makeText(localContext, registerState.value.data.toString(), Toast.LENGTH_SHORT).show()
        }
        registerState.value.error.isNotEmpty()->{
            Toast.makeText(localContext, registerState.value.error, Toast.LENGTH_SHORT).show()
        }
    }
    Column(
        modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var createPassword by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }

        OutlinedTextField(
            value = firstName,
            onValueChange = {
                firstName=it
            },
            label = {
                Text(text = "First Name")
            }
        )
        OutlinedTextField(
            value = lastName,
            onValueChange = {
                lastName=it
            },
            label = {
                Text(text = "Last Name")
            }
        )
        OutlinedTextField(
            value = email,
            onValueChange = {
                email=it
            },
            label = {
                Text(text = "Email")
            }
        )
        OutlinedTextField(
            value = createPassword,
            onValueChange = {
                createPassword=it
            },
            label = {
                Text(text = "Create Password")
            }
        )
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword=it
            },
            label = {
                Text(text = "Confirm Password")
            }
        )

        Button(
            onClick = {
                val data= UserData(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = createPassword,
                    confirmPassword = confirmPassword
                )
                viewModel.registerUserWithEmailAndPassword(data)
            }
        ) {
            Text("Sign Up")
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun SignUpScreenPreview() {

    Column(modifier=Modifier.padding(start =34.dp,top=124.dp,end=34.dp,bottom=100.dp).fillMaxSize()
        .background(Color.Cyan)) {
        Text(text = "Signup" ,fontSize = 30.sp)
        Row(
            modifier=Modifier.padding(top=24.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            OutlinedTextField(value= "", onValueChange = {}, placeholder = { Text("First Name", fontSize = 13.sp) }, modifier = Modifier.size(width = 148.dp, height = 40.dp))
            OutlinedTextField(value= "", onValueChange = {}, placeholder = { Text("Last Name") }, modifier = Modifier.size(width = 148.dp, height = 40.dp))
        }

    }
}