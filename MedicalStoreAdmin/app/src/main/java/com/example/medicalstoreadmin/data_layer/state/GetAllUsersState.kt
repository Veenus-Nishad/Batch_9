package com.example.medicalstoreadmin.data_layer.state

import com.example.medicalstoreadmin.data_layer.response.GetAllUserResponse
import retrofit2.Response

data class GetAllUsersState(
    val Loading:Boolean = false,
    val Error:String? = null,
    val Data: Response<GetAllUserResponse>? = null
)
