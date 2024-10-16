package com.example.medicalstorevendor.repository

import com.example.medicalstorevendor.State
import com.example.medicalstorevendor.data_layer.ApiProvider
import com.example.medicalstorevendor.data_layer.response.GetAllOrderDetails
import com.example.medicalstorevendor.data_layer.response.LoginResponse
import com.example.medicalstorevendor.data_layer.response.PlaceOrderResponse
import com.example.medicalstorevendor.data_layer.response.SignUpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class Repository {

    suspend fun getAllOrderDetails(): Flow<State<Response<GetAllOrderDetails>>> = flow {
        emit(State.Loading)
        try {
            val response = ApiProvider.provideApi().getAllOrdersDetail()
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }


    }

    suspend fun signUpUser(
        name: String,
        password: String,
        email: String,
        phoneNumber: String,
        address: String,
        pinCode: String,
    ): Flow<State<Response<SignUpResponse>>> = flow {
        emit(State.Loading)
        try {
            val response = ApiProvider.provideApi().signUpUser(
                name = name,
                password = password,
                email = email,
                phoneNumber = phoneNumber,
                address = address,
                pinCode = pinCode
            )
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }

    suspend fun LoginUser(
        email: String,
        password: String
    ): Flow<State<Response<LoginResponse>>> = flow {
        emit(State.Loading)
        try {
            val response = ApiProvider.provideApi().LoginUser(
                email = email,
                password = password
            )
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }

    suspend fun placeOrder(
        product_id: String,
        user_id: String,
        product_name: String,
        user_name: String,
        total_amount: String,
        price: String,
        quantity: String,
        message: String? = "No Additional Notes",
        category: String
    ): Flow<State<Response<PlaceOrderResponse>>> = flow {
        emit(State.Loading)
        try {
            val response = ApiProvider.provideApi().placeOrder(
                product_id = product_id,
                user_id = user_id,
                product_name = product_name,
                user_name = user_name,
                total_amount = total_amount,
                quantity = quantity,
                message = message,
                price = price,
                category = category
            )
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }
}

