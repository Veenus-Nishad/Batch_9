package com.example.shoppingappcustomer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingappcustomer.common.ResultState
import com.example.shoppingappcustomer.domain.models.Category
import com.example.shoppingappcustomer.domain.models.ProductModel
import com.example.shoppingappcustomer.domain.models.UserData
import com.example.shoppingappcustomer.usecase.GetCategoryUseCase
import com.example.shoppingappcustomer.usecase.GetProductsUseCase
import com.example.shoppingappcustomer.usecase.RegisterUserWithEmailAndPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val GetAllCategoryUsecase: GetCategoryUseCase,
    private val GetAllProductsUseCase: GetProductsUseCase,
    private val GetCategoryInLimitUseCase: GetCategoryUseCase,
    private val GetProductsInLimitUseCase: GetProductsUseCase,
    private val RegisterUserWithEmailAndPasswordUseCase: RegisterUserWithEmailAndPasswordUseCase
) :
    ViewModel() {

    private val _getAllCategoryState = MutableStateFlow(GetAllCategoryState())
    val getAllCategoryState = _getAllCategoryState.asStateFlow()

    private val _registerUserState = MutableStateFlow(RegisterUserState())
    val registerUserState = _registerUserState.asStateFlow()

    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState = _homeScreenState.asStateFlow()

    fun registerUserWithEmailAndPassword(userData: UserData) {
        viewModelScope.launch {
            RegisterUserWithEmailAndPasswordUseCase.registerUserWithEmailAndPassword(userData)
                .collectLatest {
                    when (it) {
                        is ResultState.Loading -> {
                            _registerUserState.value = RegisterUserState(isLoading = true)
                        }

                        is ResultState.Error -> {
                            _registerUserState.value = RegisterUserState(error = it.error)
                        }

                        is ResultState.Success -> {
                            _registerUserState.value = RegisterUserState(data = it.data)
                        }
                    }
                }
        }
    }

    init {
        loadHomeScreenData()
    }

    fun loadHomeScreenData() {
        viewModelScope.launch {
            combine(
                GetCategoryInLimitUseCase.getCategoryInLimit(),
                GetProductsInLimitUseCase.getProductsInLimit()
            ) { categoryState, productState ->
                when {
                    categoryState is ResultState.Error -> {
                        HomeScreenState(isLoading = false, error = categoryState.toString())
                    }

                    productState is ResultState.Error -> {
                        HomeScreenState(isLoading = false, error = productState.toString())
                    }

                    categoryState is ResultState.Success && productState is ResultState.Success -> {
                        HomeScreenState(
                            isLoading = false,
                            categories = categoryState.data,
                            products = productState.data
                        )
                    }

                    else -> {
                        HomeScreenState(isLoading = true)
                    }
                }
            }.collect { state ->
                _homeScreenState.value = state
            }
        }
    }

    fun getAllCategory() {
        viewModelScope.launch {
            GetAllCategoryUsecase.getAllCategoryUseCase().collectLatest {
                when (it) {
                    is ResultState.Loading -> {
                        _getAllCategoryState.value = GetAllCategoryState(isLoading = true)
                    }

                    is ResultState.Error -> {
                        _getAllCategoryState.value = GetAllCategoryState(error = it.error)
                    }

                    is ResultState.Success -> {
                        _getAllCategoryState.value = GetAllCategoryState(data = it.data)
                    }
                }
            }
        }
    }

}

data class GetAllCategoryState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<Category?> = emptyList()
)

data class HomeScreenState(
    val isLoading: Boolean = false,
    val categories: List<Category?> = emptyList(),
    val products: List<ProductModel?> = emptyList(),
    val error: String = ""
)

data class RegisterUserState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: String? = null
)