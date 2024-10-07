package com.example.newsapp.network_layer

import com.example.newsapp.network_layer.response.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String="us",
        @Query("category") category: String="business",
        @Query("apiKey") apiKey: String="ee7df9e18d564269922446233be8dff4"
    ): Response<NewsModel>
}