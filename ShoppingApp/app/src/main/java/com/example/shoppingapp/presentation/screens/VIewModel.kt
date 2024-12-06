package com.example.shoppingapp.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.common.ResultState
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

    private val _addCategoryState = MutableStateFlow(addCategorystate())
    val addCategoryState = _addCategoryState.asStateFlow()

    fun addCategory(category: category) {
        viewModelScope.launch {
            repo.addCategory(category).collectLatest {
                when (it) {
                    is ResultState.Loading -> {
                        _addCategoryState.value = addCategorystate(isLoading = true)
                    }

                    is ResultState.Success -> {
                        addCategorystate(data = it.data)
                    }

                    is ResultState.Error -> {
                        addCategorystate(error = it.error)
                    }
                }
            }
        }
    }
}

data class addCategorystate(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: String = ""
)