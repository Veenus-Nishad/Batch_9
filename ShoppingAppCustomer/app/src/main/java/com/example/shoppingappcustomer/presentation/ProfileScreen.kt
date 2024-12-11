package com.example.shoppingappcustomer.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shoppingappcustomer.R


@Composable
fun ProfileScreenUi(
    navController: NavController
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.ellipse1),
            contentDescription = null,
            modifier = Modifier
                .size(223.dp)
                .align(AbsoluteAlignment.TopRight)
        )
        Image(
            painter = painterResource(R.drawable.ellipse2),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .align(AbsoluteAlignment.BottomLeft)
        )
        Column(
            modifier = Modifier
                .padding(start = 34.dp, top =61.dp, end = 34.dp)
                .fillMaxSize()
        ) {
            var firstName by remember { mutableStateOf("") }
            var lastName by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var phoneNumber by remember { mutableStateOf("") }
            var Address  by remember { mutableStateOf("") }
            Icon(painter = painterResource(R.drawable.frock), contentDescription = null, modifier = Modifier.size(100.dp))
            Spacer(Modifier.height(35.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){


            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                placeholder = { Text("First Name") },
                modifier = Modifier.width(148.dp)
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                placeholder = { Text("Last Name") },
                modifier = Modifier.width(148.dp)
            )
        }

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 21.dp)
                )
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = {
                        phoneNumber = it
                    },
                    placeholder = { Text("Phone Number") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp)
                )
                OutlinedTextField(
                    value = Address,
                    onValueChange = { Address = it },
                    placeholder = { Text("Address") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp)
                )

                Button(
                    onClick = {}, modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF68B8B),
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Log Out")
                }
            Spacer(modifier = Modifier.height(22.dp))
                Button(
                    onClick = {},
                    border = BorderStroke(2.dp, Color(0xFFF68B8B)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    contentPadding = PaddingValues(horizontal = 0.dp)
                ) {
                    Text("Edit Profile")
                }
            }

        }

    }