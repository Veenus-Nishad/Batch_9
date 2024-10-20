package com.example.e_book.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_book.ResultState
import com.example.e_book.data.repository.Repository
import com.example.e_book.data.response.BookModels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repository: Repository) :ViewModel(){

    val _getAllBooksState = MutableStateFlow(GetAllBooksState())
    val getAllBooksState=_getAllBooksState.asStateFlow()

    fun getAllBooks(){
        viewModelScope.launch (Dispatchers.IO){
            repository.getAllBooks().collect{
                when(it){
                    is ResultState.Loading->{
                        _getAllBooksState.value=GetAllBooksState(isLoading = true)
                    }
                    is ResultState.Error->{
                        _getAllBooksState.value=GetAllBooksState(isLoading = false, error = it.exception)
                    }
                    is ResultState.Success->{
                        _getAllBooksState.value=GetAllBooksState(isLoading = false, data = it.data)
                    }
                }
            }
        }
    }

}

data class GetAllBooksState(
    val isLoading : Boolean =  false ,
    val data : List<BookModels> = emptyList(),
    val error : Throwable?= null,
)