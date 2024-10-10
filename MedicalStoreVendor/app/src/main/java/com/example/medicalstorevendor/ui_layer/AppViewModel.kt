package com.example.medicalstorevendor.ui_layer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalstorevendor.data_layer.response.LoginResponse
import com.example.medicalstorevendor.data_layer.response.SignUpResponse
import com.example.medicalstorevendor.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val signUpUserState = mutableStateOf<Response<SignUpResponse>?>(null)
    val loginUserState = mutableStateOf<Response<LoginResponse>?>(null)
    fun SignUp(
        name: String,
        password: String,
        email: String,
        phoneNumber: String,
        address: String,
        pinCode: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            signUpUserState.value = repository.signUpUser(
                name = name,
                password = password,
                email = email,
                phoneNumber = phoneNumber,
                address = address,
                pinCode = pinCode
            )
        }
    }

    fun SignIn(
        email: String,
        password: String,
    ){
        viewModelScope.launch(Dispatchers.IO) {
            loginUserState.value = repository.signInUser(
                email = email,
                password = password
            )
        }
    }
}

//data class  SignUpState(
//    val Loading:Boolean=false,
//    val Error:String?=null,
//    val Data:Response<SignUpResponse>?=null
//
//)