package com.example.medicalstoreadmin.repository

import com.example.medicalstoreadmin.State
import com.example.medicalstoreadmin.data_layer.ApiProvider
import com.example.medicalstoreadmin.data_layer.response.GetAllUserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class Repository{
    suspend fun getAllUsers(): Flow<State<Response<GetAllUserResponse>>> = flow {
            emit(State.Loading)
        try{
            val response = ApiProvider.provideApi().getAllUsers()
            emit(State.Success(response))
        }catch(e:Exception){
            emit(State.Error(e.message.toString()))
        }
    }
}