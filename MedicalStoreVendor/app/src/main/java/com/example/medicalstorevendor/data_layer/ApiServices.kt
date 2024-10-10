package com.example.medicalstorevendor.data_layer

import com.example.medicalstorevendor.data_layer.response.LoginResponse
import com.example.medicalstorevendor.data_layer.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServices {
    @FormUrlEncoded
    @POST("createUser")
    suspend fun signUpUser (
        @Field("name") name:String,
        @Field("password") password:String,
        @Field("email") email:String,
        @Field("phoneNumber") phoneNumber:String,
        @Field("address") address:String,
        @Field("pinCode") pinCode:String,
    ): retrofit2.Response<SignUpResponse>

    @FormUrlEncoded
    @POST("login")
    suspend fun signInUser (
        @Field("email") email:String,
        @Field("password") password:String,
    ):Response<LoginResponse>
}