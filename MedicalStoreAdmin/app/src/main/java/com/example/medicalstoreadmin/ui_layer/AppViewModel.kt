package com.example.medicalstoreadmin.ui_layer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalstoreadmin.State
import com.example.medicalstoreadmin.data_layer.response.AddProductResponse
import com.example.medicalstoreadmin.data_layer.response.GetAllUserResponse
import com.example.medicalstoreadmin.data_layer.response.UpdateAllUsersDetailResponse
import com.example.medicalstoreadmin.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _getAllUsersState = MutableStateFlow(GetAllUsersState())
    val getAllUsersState = _getAllUsersState.asStateFlow()

    private val _addProductsState = MutableStateFlow(AddProductsState())
    val addProductState = _addProductsState.asStateFlow()

    private val _updateUserAllDetailsState = MutableStateFlow(UpdateUserAllDetailsState())
    val updateUserAllDetailsState = _updateUserAllDetailsState.asStateFlow()

    fun approveUser(
        user_id: String,
        isApproved: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUserAllDetails(
                user_id = user_id,
                isApproved = isApproved
            ).collect { state ->
                when (state) {
                    is State.Loading -> {
                        _updateUserAllDetailsState.value = UpdateUserAllDetailsState(Loading = true)
                    }

                    is State.Success -> {
                        _updateUserAllDetailsState.value =
                            UpdateUserAllDetailsState(Data = state.data, Loading = false)
                    }

                    is State.Error -> {
                        _updateUserAllDetailsState.value =
                            UpdateUserAllDetailsState(Error = state.message, Loading = false)
                    }
                }
            }
        }

    }

    fun addProduct(
        productName: String,
        productPrice: String,
        productCategory: String,
        productStock: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(
                productName = productName,
                productPrice = productPrice,
                productCategory = productCategory,
                productStock = productStock,
            ).collect { state ->
                when (state) {
                    is State.Loading -> {
                        _addProductsState.value = AddProductsState(Loading = true)
                    }

                    is State.Success -> {
                        _addProductsState.value =
                            AddProductsState(Data = state.data, Loading = false)
                    }

                    is State.Error -> {
                        _addProductsState.value =
                            AddProductsState(Error = state.message, Loading = false)
                    }
                }
            }
        }
    }

    init {
        getAllUser()
    }

    fun getAllUser() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllUsers().collect { state ->
                when (state) {
                    is State.Loading -> {
                        _getAllUsersState.value = GetAllUsersState(Loading = true)
                    }

                    is State.Success -> {
                        _getAllUsersState.value =
                            GetAllUsersState(Data = state.data, Loading = false)
                    }

                    is State.Error -> {
                        _getAllUsersState.value =
                            GetAllUsersState(Error = state.message, Loading = false)
                    }
                }

            }
        }
    }
}

data class GetAllUsersState(
    val Loading: Boolean = false,
    val Error: String? = null,
    val Data: Response<GetAllUserResponse>? = null
)

data class AddProductsState(
    val Loading: Boolean = false,
    val Error: String? = null,
    val Data: Response<AddProductResponse>? = null
)

data class UpdateUserAllDetailsState(
    val Loading: Boolean = false,
    val Error: String? = null,
    val Data: Response<UpdateAllUsersDetailResponse>? = null
)