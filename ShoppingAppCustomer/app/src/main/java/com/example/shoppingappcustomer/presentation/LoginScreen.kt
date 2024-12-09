package com.example.shoppingappcustomer.presentation

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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

@Composable
fun LoginScreenPreview(
    viewModel: ViewModel = hiltViewModel()
) {

    val loginState = viewModel.loginUserState.collectAsState()

    val localContext = LocalContext.current

    when {
        loginState.value.isLoading -> {
            CircularProgressIndicator()
        }

        loginState.value.data?.isNotEmpty() == true -> {
            Toast.makeText(localContext, loginState.value.data.toString(), Toast.LENGTH_SHORT)
                .show()
        }

        loginState.value.error.isNotEmpty() -> {
            Toast.makeText(localContext, loginState.value.error, Toast.LENGTH_SHORT).show()
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

            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            Text(text = "Login", fontSize = 30.sp, modifier = Modifier.padding(bottom = 36.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                placeholder = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )


            OutlinedTextField(
                value = password,
                onValueChange = {password=it},
                placeholder = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 21.dp)
            )

            Text(
                "Forgot Password?",
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 20.dp)
                    .clickable(enabled = true, onClick = {})
            )


            Button(
                onClick = {}, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 44.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF68B8B),
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Login")
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 37.dp)
            ) {
                Text("Don't have an Account?")
                Text(
                    "Sign Up",
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
                border = BorderStroke(2.dp, Color(0xFFF68B8B)), shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp),
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
                border = BorderStroke(2.dp, Color(0xFFF68B8B)), shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
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
        Image(
            painter = painterResource(R.drawable.ellipse2),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .align(AbsoluteAlignment.BottomLeft)
        )
    }
}