package com.example.shoppingapp.presentation.screens

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.common.ResultState
import com.example.shoppingapp.domain.models.ProductModels
import com.example.shoppingapp.domain.models.category
import com.example.shoppingapp.domain.repo.repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class myViewModel @Inject constructor(
    private val repo: repo
) : ViewModel() {

    private val _addCategory = MutableStateFlow(addCategoryState())
    val addCategory = _addCategory.asStateFlow()

    private val _getCategory = MutableStateFlow(getCategoryState())
    val getCategory = _getCategory.asStateFlow()

    private val _addProduct = MutableStateFlow(addProductState())
    val addProducts = _addProduct.asStateFlow()

    private val _uploadProductImage = MutableStateFlow(uploadProductImageState())
    val uploadProductImage = _uploadProductImage.asStateFlow()

    fun addCategory(category: category) {
        viewModelScope.launch {
            repo.addCategory(category).collectLatest {
                when (it) {
                    is ResultState.Loading -> {
                        _addCategory.value = addCategoryState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _addCategory.value = addCategoryState(data = it.data)
                    }

                    is ResultState.Error -> {
                        _addCategory.value = addCategoryState(error = it.error)
                    }
                }
            }
        }
    }

    fun getCategory() {
        viewModelScope.launch {
            repo.getCategories().collectLatest {
                when (it) {
                    is ResultState.Success -> {
                        _getCategory.value = getCategoryState(data = it.data)
                    }

                    is ResultState.Error -> {
                        _getCategory.value = getCategoryState(error = it.error)
                    }

                    is ResultState.Loading -> {
                        _getCategory.value = getCategoryState(isLoading = true)
                    }
                }
            }
        }
    }

    fun addProduct(product: ProductModels) {
        viewModelScope.launch {
            repo.addProduct(product).collectLatest {
                when (it) {
                    is ResultState.Error -> {
                        _addProduct.value = addProductState(error = it.error)
                    }

                    is ResultState.Loading -> {
                        _addProduct.value = addProductState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _addProduct.value = addProductState(data = it.data)
                    }

                }
            }
        }
    }

    fun uploadProductImage(imageUri: Uri) {
        viewModelScope.launch {
            repo.addImage(imageUri).collectLatest {
                when (it) {
                    is ResultState.Error -> {
                        _uploadProductImage.value = uploadProductImageState(error = it.error)
                    }

                    is ResultState.Loading -> {
                        _uploadProductImage.value = uploadProductImageState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _uploadProductImage.value = uploadProductImageState(data = it.data)
                    }
                }
            }
        }
    }
}


data class addCategoryState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: String = ""
)

data class getCategoryState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<category> = emptyList()
)

data class addProductState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: String = ""
)

data class uploadProductImageState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: String = ""
)