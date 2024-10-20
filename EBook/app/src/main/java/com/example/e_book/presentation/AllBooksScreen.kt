package com.example.e_book.presentation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.data.EmptyGroup.data
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.e_book.viewModel.AppViewModel

@Composable
fun AllBooksScreen(
    viewModel: AppViewModel = hiltViewModel()
) {
    val state = viewModel.getAllBooksState.collectAsState()
    val data = state.value.data ?: emptyList()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllBooks()
    }

    when {
        state.value.isLoading -> {
        }

        state.value.error != null -> {}
        state.value.data !=null -> {
            Column(modifier=Modifier.fillMaxSize()) {  LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(data) {
                    books(
                        title = it.bookName,
                        bookImage = it.bookImage,
                        url = it.bookUrl,
                        author = it.bookAuthor,
                        category = it.bookCategory
                    )
                }
            } }

        }
    }
}

@Composable
fun books(
    title: String,
    bookImage: String,
    url: String,
    author: String,
    category: String
) {
    Card(modifier = Modifier.fillMaxWidth().height(250.dp).padding(10.dp)) {
        Column {
            AsyncImage(model = bookImage, contentDescription = title, contentScale = ContentScale.Crop)

            Text(text = title)
            Text(text = author)
            Text(text = category)
           // Text(text = url)

        }


    }
}