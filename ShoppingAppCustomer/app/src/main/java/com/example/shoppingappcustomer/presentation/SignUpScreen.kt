package com.example.shoppingappcustomer.presentation

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingappcustomer.R
import com.example.shoppingappcustomer.domain.models.UserData

@Composable
fun SignUpScreenPreview(
    viewModel: ViewModel = hiltViewModel()
) {

    val registerState = viewModel.registerUserState.collectAsState()

    val localContext = LocalContext.current

    when {
        registerState.value.isLoading -> {
            CircularProgressIndicator()
        }

        registerState.value.data?.isNotEmpty() == true -> {
            Toast.makeText(localContext, registerState.value.data.toString(), Toast.LENGTH_SHORT)
                .show()
        }

        registerState.value.error.isNotEmpty() -> {
            Toast.makeText(localContext, registerState.value.error, Toast.LENGTH_SHORT).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.ellipse1),
            contentDescription = null,
            modifier = Modifier
                .size(223.dp)
                .align(AbsoluteAlignment.TopRight)
        )
        Column(
            modifier = Modifier
                .padding(start = 34.dp, top = 124.dp, end = 34.dp, bottom = 100.dp)
                .fillMaxSize()
        ) {

            Text(text = "Signup", fontSize = 30.sp)
            Row(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var firstName by remember { mutableStateOf("") }
                var lastName by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }
                var createPassword by remember { mutableStateOf("") }
                var confirmPassword by remember { mutableStateOf("") }

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

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 21.dp)
                )
                OutlinedTextField(
                    value = createPassword,
                    onValueChange = {
                        createPassword = it
                    },
                    placeholder = { Text("Create Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp)
                )
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    placeholder = { Text("Confirm Password") },
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
                    Text(text = "Signup")
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 29.dp)
                ) {
                    Text("Already have an Account?")
                    Text(
                        "Login",
                        color = Color(0xFFF68B8B),
                        modifier = Modifier.clickable(enabled = true, onClick = {})
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = "OR", modifier = Modifier.padding(horizontal = 20.dp))
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )

                }
                Button(
                    onClick = {},
                    border = BorderStroke(2.dp, Color(0xFFF68B8B)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    contentPadding = PaddingValues(horizontal = 0.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.facebook),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)

                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Login with Facebook")
                    }


                }

                Button(
                    onClick = {},
                    border = BorderStroke(2.dp, Color(0xFFF68B8B)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    contentPadding = PaddingValues(horizontal = 0.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.google),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)

                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Login with Google")
                    }


                }
            }

        }
        Image(
            painter = painterResource(R.drawable.ellipse2),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .align(AbsoluteAlignment.BottomLeft)
        )
    }
}