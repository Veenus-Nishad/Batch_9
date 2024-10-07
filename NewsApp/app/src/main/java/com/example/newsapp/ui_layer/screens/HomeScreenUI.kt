package com.example.newsapp.ui_layer.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.newsapp.ui_layer.NewsViewModel
import com.example.newsapp.ui_layer.components.CardItems

@Composable
fun HomeScreenUI(viewModel: NewsViewModel) {
    val data = viewModel.data.value?.articles ?: emptyList()
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(data) { news ->
            CardItems(
                title = news.title ?: "No Title Available",
                description = news.description ?: "No Description Available",
                urlToImage = news.urlToImage?:"No Image Available"
            )
        }
    }
}