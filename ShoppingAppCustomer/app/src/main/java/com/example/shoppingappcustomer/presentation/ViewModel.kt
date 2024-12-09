package com.example.shoppingappcustomer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingappcustomer.common.ResultState
import com.example.shoppingappcustomer.domain.models.Category
import com.example.shoppingappcustomer.domain.models.ProductModel
import com.example.shoppingappcustomer.domain.models.UserData
import com.example.shoppingappcustomer.usecase.AuthUserWithEmailAndPasswordUseCase
import com.example.shoppingappcustomer.usecase.GetCategoryUseCase
import com.example.shoppingappcustomer.usecase.GetProductsUseCase
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
    private val RegisterUserWithEmailAndPasswordUseCase: AuthUserWithEmailAndPasswordUseCase,
    private val LoginUserWithEmailAndPasswordUseCase: AuthUserWithEmailAndPasswordUseCase
) :
    ViewModel() {

    private val _getAllCategoryState = MutableStateFlow(GetAllCategoryState())
    val getAllCategoryState = _getAllCategoryState.asStateFlow()

    private val _registerUserState = MutableStateFlow(RegisterUserState())
    val registerUserState = _registerUserState.asStateFlow()

    private val _loginUserState = MutableStateFlow(LoginUserState())
    val loginUserState = _loginUserState.asStateFlow()

    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState = _homeScreenState.asStateFlow()


    fun loginUserWithEmailAndPassword(userData: UserData) {
        viewModelScope.launch {
            LoginUserWithEmailAndPasswordUseCase.loginUserWithEmailAndPassword(userData).collect {
                when (it) {
                    is ResultState.Loading -> {
                        _loginUserState.value = LoginUserState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _loginUserState.value = LoginUserState(data = it.data)
                    }

                    is ResultState.Error -> {
                        _loginUserState.value = LoginUserState(error = it.error)
                    }
                }
            }

        }
    }

    fun registerUserWithEmailAndPassword(userData: UserData) {
        viewModelScope.launch {
            RegisterUserWithEmailAndPasswordUseCase.registerUserWithEmailAndPassword(userData)
                .collect {
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

  private  fun loadHomeScreenData() {
        viewModelScope.launch {
            combine(
                GetCategoryInLimitUseCase.getCategoryInLimit(),
                GetProductsInLimitUseCase.getProductsInLimit()
            ) { categoryState, productState ->
                when {
                    categoryState is ResultState.Error -> {
                        HomeScreenState(isLoading = false, error = categoryState.error)
                    }

                    productState is ResultState.Error -> {
                        HomeScreenState(isLoading = false, error = productState.error)
                    }

                    categoryState is ResultState.Success && productState is ResultState.Success -> {
                        HomeScreenState(
                            isLoading = false,
                            categories = categoryState.data,
                            products = productState.data
                        )
                    }

                    categoryState is ResultState.Loading || productState is ResultState.Loading -> {
                        HomeScreenState(isLoading = true) // Only return the loading state
                    }
                    else -> {
                        HomeScreenState() // Default fallback state if no condition is met
                    }
                }
            }.collect { newstate ->
                _homeScreenState.value = newstate
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
                        _getAllCategoryState.value = GetAllCategoryState(categoriesData = it.data)
                    }
                }
            }
        }
    }

}

data class GetAllCategoryState(
    val isLoading: Boolean = false,
    val error: String = "",
    val categoriesData: List<Category?> = emptyList()
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

data class LoginUserState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: String? = null

)