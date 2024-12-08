package com.example.shoppingappcustomer.presentation

import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: ViewModel = hiltViewModel()
) {
    val state = viewModel.getAllCategoryState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllCategory()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Center
    ) {
        LazyRow {
            items(state.value.data) {
                Text(text = it!!.name)
                Text(text = it.imageUri)
            }
        }
    }

}