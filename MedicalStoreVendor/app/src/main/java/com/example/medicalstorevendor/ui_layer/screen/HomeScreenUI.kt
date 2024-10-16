package com.example.medicalstorevendor.ui_layer.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.medicalstorevendor.ui_layer.AppViewModel
import com.example.medicalstorevendor.ui_layer.navigation.PlaceOrderScreen


@Composable
fun HomeScreenUI( navController: NavController,viewModel: AppViewModel = hiltViewModel(),) {
    val response = viewModel.getAllOrdersDetailState.collectAsState()
    val data = response.value.Data?.body() ?: emptyList()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(PlaceOrderScreen) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(data!!) { OrderDetails ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Text(text = "Name${OrderDetails.product_name}")
                        Text(text = "Order ID${OrderDetails.order_id}")
                        Text(text = "Price${OrderDetails.price}")
                        Text(text = "Quantity${OrderDetails.quantity}")
                        Text(text = "Total Amount${OrderDetails.total_amount}")
                    }
                }

            }

        }
    }
}
