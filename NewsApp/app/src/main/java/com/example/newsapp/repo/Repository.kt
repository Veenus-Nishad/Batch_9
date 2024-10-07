package com.example.newsapp.repo

import com.example.newsapp.network_layer.ApiProvider

class Repository {
    suspend fun getNewsRepo()= ApiProvider.ProvideApi().getNews()
}