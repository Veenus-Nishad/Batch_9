package com.example.newsapp.network_layer

import com.example.newsapp.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {
    fun ProvideApi() = Retrofit.Builder().baseUrl(BASE_URL).client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create()).build().create(ApiServices::class.java)
}