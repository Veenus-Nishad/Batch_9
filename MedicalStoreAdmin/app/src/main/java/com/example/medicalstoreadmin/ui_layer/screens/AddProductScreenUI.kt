package com.example.medicalstoreadmin.ui_layer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medicalstoreadmin.ui_layer.AppViewModel

@Preview(showBackground = true)
@Composable
fun AddProductScreenUI(viewModel: AppViewModel =hiltViewModel()) {

    val state=viewModel.addProductState.collectAsState()

    var productName by remember { mutableStateOf("")}
    var productPrice by remember { mutableStateOf("")}
    var productStock by remember { mutableStateOf("")}
    var productCategory by remember { mutableStateOf("")}

    when {
        state.value.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }}

        state.value.Error != null -> {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = state.value.Error.toString())
        }
        }
        state.value.Data!=null->{
            LaunchedEffect(Unit) {
                productName=""
                productPrice=""
                productCategory=""
                productStock=""
            }
        }
    }

    Column(
        modifier= Modifier.padding(8.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        OutlinedTextField(
            value = productName,
            onValueChange = {productName = it},
            label = { Text("Product Name") }
        )
        OutlinedTextField(
            value = productPrice,
            onValueChange = {productPrice = it},
            label = { Text("Product Price") }
        )
        OutlinedTextField(
            value = productCategory,
            onValueChange = {productCategory=it},
            label = { Text("Product Stock") }
        )
        OutlinedTextField(
            value = productStock,
            onValueChange = {productStock=it},
            label = { Text("Product Category") }
        )
        Button(onClick = {
            if (productName.isNotEmpty() && productPrice.isNotEmpty() && productStock.isNotEmpty() && productCategory.isNotEmpty()){
                viewModel.addProduct(
                    productName=productName,
                    productPrice=productPrice,
                    productStock=productStock,
                    productCategory=productCategory
                )
            }

        }, modifier = Modifier.padding(8.dp)){
            Text(text = "Add Product")
        }
    }
}