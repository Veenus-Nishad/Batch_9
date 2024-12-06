package com.example.medicalstorevendor.ui_layer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalstorevendor.State
import com.example.medicalstorevendor.data_layer.response.GetAllOrderDetails
import com.example.medicalstorevendor.data_layer.response.LoginResponse
import com.example.medicalstorevendor.data_layer.response.PlaceOrderResponse
import com.example.medicalstorevendor.data_layer.response.SignUpResponse
import com.example.medicalstorevendor.repository.Repository
import com.example.medicalstorevendor.user_preferences.UserPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repository: Repository, private val userPreferencesManager:UserPreferencesManager) : ViewModel() {

    private val _signUpUserState = MutableStateFlow(SignUpState())
    val signUpUserState = _signUpUserState.asStateFlow()

    private val _loginUserState = MutableStateFlow(LoginState())
    val loginUserState = _loginUserState.asStateFlow()

    private val _placeOrderState = MutableStateFlow(PlaceOrderState())
    val placeOrderState = _placeOrderState.asStateFlow()

    private val _getAllOrdersDetailState = MutableStateFlow(GetAllOrdersDetailState())
    val getAllOrdersDetailState = _getAllOrdersDetailState.asStateFlow()

    fun getAllOrdersDetail(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllOrderDetails().collect{state->
                when(state){
                    is State.Loading->{
                        _getAllOrdersDetailState.value=GetAllOrdersDetailState(Loading = true)
                    }
                    is State.Success->{

                        _getAllOrdersDetailState.value=GetAllOrdersDetailState(Data = state.data)
                    }
                    is State.Error->{
                        _getAllOrdersDetailState.value=GetAllOrdersDetailState(Error = state.message)
                    }
                }
            }
        }
    }

    fun placeOrder(
        product_id: String,
        user_id: String,
        product_name: String,
        user_name: String,
        total_amount: String,
        price: String,
        quantity: String,
        message: String? = "No Additional Notes",
        category: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.placeOrder(
                product_id = product_id,
                user_id = user_id,
                product_name = product_name,
                user_name = user_name,
                total_amount = total_amount,
                price = price,
                quantity = quantity,
                message = message,
                category = category
            ).collect { state ->
                when (state) {
                    is State.Loading -> {
                        _placeOrderState.value = PlaceOrderState(Loading = true)
                    }

                    is State.Success -> {
                        _placeOrderState.value = PlaceOrderState(Data = state.data)
                    }

                    is State.Error -> {
                        _placeOrderState.value = PlaceOrderState(Error = state.message)

                    }
                }
            }
        }
    }

    fun SignUp(
        name: String,
        password: String,
        email: String,
        phoneNumber: String,
        address: String,
        pinCode: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.signUpUser(
                name = name,
                password = password,
                email = email,
                phoneNumber = phoneNumber,
                address = address,
                pinCode = pinCode
            ).collect { state ->
                when (state) {
                    is State.Loading -> {
                        _signUpUserState.value = SignUpState(Loading = true)
                    }

                    is State.Success -> {
                        _signUpUserState.value = SignUpState(Data = state.data)
                    }

                    is State.Error -> {
                        _signUpUserState.value = SignUpState(Error = state.message)

                    }
                }
            }
        }
    }

    fun login(
        email: String,
        password: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.LoginUser(
                email = email,
                password = password
            ).collect{state->
                when(state){
                    is State.Loading->{
                        _loginUserState.value=LoginState(Loading = true)
                    }
                    is State.Success->{
                        val userId=state.data.message()
                        userPreferencesManager.saveUserId(userId) // login success hote hi save kara diya
                        _loginUserState.value=LoginState(Data = state.data)
                    }
                    is State.Error->{
                        _loginUserState.value=LoginState(Error = state.message)

                    }                    }
            }
        }
    }
}

data class SignUpState(
    val Loading: Boolean = false,
    val Error: String? = null,
    val Data: Response<SignUpResponse>? = null

)

data class LoginState(
    val Loading: Boolean = false,
    val Error: String? = null,
    val Data: Response<LoginResponse>? = null

)

data class PlaceOrderState(
    val Loading: Boolean = false,
    val Error: String? = null,
    val Data: Response<PlaceOrderResponse>? = null
)

data class GetAllOrdersDetailState(
    val Loading: Boolean = false,
    val Error: String? = null,
    val Data: Response<GetAllOrderDetails>? = null

)