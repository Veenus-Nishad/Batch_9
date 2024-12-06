package com.example.shoppingapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp.domain.models.category


@Composable
fun AddCategoryScreen(
    viewModel: myViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val state = viewModel.addCategory.collectAsState()

        var categoryName by remember {
            mutableStateOf("")
        }

        var categoryImage by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = categoryName,
            onValueChange = { categoryName = it },
            label = { Text(text = "Category Name") }
        )

        OutlinedTextField(
            value = categoryImage,
            onValueChange = { categoryImage = it },
            label = { Text(text = "Category Image") }
        )
        Button(
            onClick = {
                val data = category(
                    name = categoryName,
                    imageUri = categoryImage
                )
                viewModel.addCategory(
                    data
                )
            }
        ) { Text(text="Add Category") }
    }
}