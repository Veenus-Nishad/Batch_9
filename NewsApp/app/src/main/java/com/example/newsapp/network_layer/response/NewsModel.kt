package com.example.newsapp.network_layer.response

data class NewsModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)