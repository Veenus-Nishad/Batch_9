package com.example.shoppingappcustomer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingappcustomer.common.ResultState
import com.example.shoppingappcustomer.domain.models.Category
import com.example.shoppingappcustomer.usecase.GetAllCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val GetAllCategoryUsecase: GetAllCategoryUseCase
) :
    ViewModel() {

        private val _getAllCategoryState= MutableStateFlow(GetAllCategoryState())
        val getAllCategoryState=_getAllCategoryState.asStateFlow()

    fun getAllCategory(){
        viewModelScope.launch {
            GetAllCategoryUsecase.getAllCategoryUseCase().collectLatest {
                when(it){
                    is ResultState.Loading->{
                        _getAllCategoryState.value=GetAllCategoryState(isLoading = true)
                    }
                    is ResultState.Error->{
                        _getAllCategoryState.value=GetAllCategoryState(error = it.error)
                    }
                    is ResultState.Success->{
                        _getAllCategoryState.value=GetAllCategoryState(Success = it.data)
                    }

                }
            }
        }
    }

}

data class GetAllCategoryState(
    val isLoading:Boolean=false,
    val error :String="",
    val Success:List<Category?> = emptyList()
)