package com.example.medicalstorevendor.repository

import com.example.medicalstorevendor.State
import com.example.medicalstorevendor.data_layer.ApiProvider
import com.example.medicalstorevendor.data_layer.response.LoginResponse
import com.example.medicalstorevendor.data_layer.response.SignUpResponse
import retrofit2.Response

class Repository {
    suspend fun signUpUser(
        name: String,
        password: String,
        email: String,
        phoneNumber: String,
        address: String,
        pinCode: String,
    ): Response<SignUpResponse> {
        State.Loading<SignUpResponse>()
        val response = ApiProvider.provideApi().signUpUser(
            name = name,
            password = password,
            email = email,
            phoneNumber = phoneNumber,
            address = address,
            pinCode = pinCode
        )
        if (response.isSuccessful) {
            // Handle successful response
            response.body()!!
        } else {
            response.message()
        }
        return response
    }

    suspend fun signInUser(
        email: String,
        password: String
    ):Response<LoginResponse>{
        State.Loading<LoginResponse>()
        val response=ApiProvider.provideApi().signInUser(
            email = email,
            password =password
        )
        if (response.isSuccessful) {
            // Handle successful response
            response.body()!!
        } else {
            response.message()
        }
        return response
    }

}

