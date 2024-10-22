package com.example.e_book.presentation

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.e_book.presentation.navigation.Routes
import com.example.e_book.presentation.viewModel.AppViewModel

@Composable
fun BookCategory(
    viewModel: AppViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.getAllCategoriesState.collectAsState()
    val data = state.value.data ?: emptyList()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllCategories()
    }

    when {
        state.value.isLoading -> {
          Box(modifier = Modifier.fillMaxWidth( ), contentAlignment = Alignment.Center){
              CircularProgressIndicator()
          }
        }

        state.value.error != null -> {
           // Text(text = "Error : ${error(state.value.error!!)}")
        }

        state.value.data.isNotEmpty() -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(data) {
                    Categories(
                        title = it.name,
                        categoryImage = it.categoryImageUrl,
                        onClick = {navController.navigate(Routes.BookByCategory(category = it.name))}
                    )
                }
            }
        }

    }
}

@Composable
fun Categories(
    title: String,
    categoryImage: String,
    onClick: () -> Unit,
) {
    Card(modifier = Modifier.fillMaxSize().clickable{
        onClick()
    }) {
        Column {
            TextOverImage(
                title = title,
                bookImage = categoryImage
            )
        }
    }
}