package com.example.medicalstorevendor.ui_layer.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medicalstorevendor.ui_layer.AppViewModel

@Composable
fun PlaceOrderScreenUI(
    viewModel: AppViewModel = hiltViewModel()
) {
    val state = viewModel.placeOrderState.collectAsState()

    var product_id by remember { mutableStateOf("") }
    var user_id by remember { mutableStateOf("") }
    var product_name by remember { mutableStateOf("") }
    var user_name by remember { mutableStateOf("") }
    var total_amount by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    when {
        state.value.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.value.Error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.value.Error.toString())
            }
        }

        state.value.Data != null -> {
            LaunchedEffect(Unit) {
                product_id = ""
                user_id = ""
                product_name = ""
                user_name = ""
                total_amount = ""
                price = ""
                quantity = ""
                message = ""
                category = ""
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        OutlinedTextField(
            value = product_id,
            onValueChange = { product_id = it },
            placeholder = { Text("Enter Product ID") }
        )
        OutlinedTextField(
            value = user_id,
            onValueChange = { user_id = it },
            placeholder = { Text("Enter User ID") }
        )
        OutlinedTextField(
            value = product_name,
            onValueChange = { product_name = it },
            placeholder = { Text("Enter Product Name") }
        )
        OutlinedTextField(
            value = user_name,
            onValueChange = { user_name = it },
            placeholder = { Text("Enter User Name") }
        )
        OutlinedTextField(
            value = total_amount,
            onValueChange = { total_amount = it },
            placeholder = { Text("Enter Total Amount") }
        )
        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            placeholder = { Text("Enter Price") }
        )
        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            placeholder = { Text("Enter Quantity") }
        )
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            placeholder = { Text("Enter Message") }
        )
        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            placeholder = { Text("Enter Category") }
        )

        Button(onClick = {
            if (product_id.isEmpty() && user_id.isEmpty() && product_name.isEmpty() && user_name.isEmpty() && total_amount.isEmpty() && price.isEmpty() && quantity.isEmpty() && message.isEmpty() && category.isEmpty())
                viewModel.placeOrder(
                    product_id = product_id,
                    user_id = user_id,
                    product_name = product_name,
                    user_name = user_name,
                    total_amount = total_amount,
                    price = price,
                    quantity = quantity,
                    message = message,
                    category = category
                )
        }) {
            Text(text = "Place Order")
        }
    }
}