package com.example.medicalstoreadmin.repository

import com.example.medicalstoreadmin.State
import com.example.medicalstoreadmin.data_layer.ApiProvider
import com.example.medicalstoreadmin.data_layer.response.AddProductResponse
import com.example.medicalstoreadmin.data_layer.response.GetAllUserResponse
import com.example.medicalstoreadmin.data_layer.response.UpdateAllUsersDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class Repository {
    suspend fun getAllUsers(): Flow<State<Response<GetAllUserResponse>>> = flow {
        emit(State.Loading)
        try {
            val response = ApiProvider.provideApi().getAllUsers()
            emit(State.Success(response))
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }

    suspend fun addProduct(
        productName: String,
        productPrice: String,
        productCategory: String,
        productStock: String,
    ): Flow<State<Response<AddProductResponse>>> = flow {
        emit(State.Loading)
        try {
            val response = ApiProvider.provideApi().addProduct(
                name = productName,
                price = productPrice,
                category = productCategory,
                stock = productStock,
            )
            emit(State.Success(response))
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }

    suspend fun updateUserAllDetails(
        user_id: String,
        isApproved: Int,
    ): Flow<State<Response<UpdateAllUsersDetailResponse>>> = flow {
        emit(State.Loading)
        try {
            val response = ApiProvider.provideApi().updateUserAllDetails(
                user_id = user_id,
                isApproved = isApproved,
            )
            emit(State.Success(response))
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }
}

