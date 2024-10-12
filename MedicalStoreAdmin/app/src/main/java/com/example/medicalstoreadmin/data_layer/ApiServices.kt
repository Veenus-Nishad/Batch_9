package com.example.medicalstoreadmin.data_layer

import com.example.medicalstoreadmin.data_layer.response.AddProductResponse
import com.example.medicalstoreadmin.data_layer.response.GetAllUserResponse
import com.example.medicalstoreadmin.data_layer.response.UpdateAllUsersDetailResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiServices {
    @GET("getAllUsers")
    suspend fun getAllUsers():Response<GetAllUserResponse>

    @FormUrlEncoded
    @PATCH("updateUserAllDetails")
    suspend fun updateUserAllDetails(
        @Field("user_id")user_id:String,
        @Field ("isApproved")isApproved:Int,
    ):Response<UpdateAllUsersDetailResponse>

    @FormUrlEncoded
    @POST("addProduct")
    suspend fun addProduct(
        @Field("name")name:String,
        @Field("price")price:String,
        @Field("category")category:String,
        @Field("stock")stock:String,
    ):Response<AddProductResponse>


}