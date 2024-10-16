package com.example.medicalstorevendor.data_layer

import com.example.medicalstorevendor.data_layer.response.GetAllOrderDetails
import com.example.medicalstorevendor.data_layer.response.LoginResponse
import com.example.medicalstorevendor.data_layer.response.PlaceOrderResponse
import com.example.medicalstorevendor.data_layer.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
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
    suspend fun LoginUser (
        @Field("email") email:String,
        @Field("password") password:String,
    ):Response<LoginResponse>

    @FormUrlEncoded
    @POST("addOrderDetails")
    suspend fun placeOrder(
        @Field("product_id") product_id:String,
        @Field("user_id") user_id:String,
        @Field("product_name") product_name:String,
        @Field("user_name") user_name:String,
        @Field("total_amount") total_amount:String,
        @Field("quantity") quantity:String,
        @Field("message") message:String?="No Additional Notes",
        @Field("price") price:String,
        @Field("category") category:String,
    ):Response<PlaceOrderResponse>

    @GET("getAllOrdersDetail")
    suspend fun getAllOrdersDetail():Response<GetAllOrderDetails>
}