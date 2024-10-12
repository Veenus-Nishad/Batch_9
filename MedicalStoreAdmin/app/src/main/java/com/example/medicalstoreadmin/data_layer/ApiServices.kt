package com.example.medicalstoreadmin.data_layer

import com.example.medicalstoreadmin.data_layer.response.GetAllUserResponse
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface ApiServices {
    @GET("getAllUsers")
    suspend fun getAllUsers():Response<GetAllUserResponse>
}