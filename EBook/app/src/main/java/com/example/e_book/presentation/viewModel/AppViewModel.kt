package com.example.e_book.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_book.data.ResultState
import com.example.e_book.data.repository.Repository
import com.example.e_book.data.response.BookCategoryModels
import com.example.e_book.data.response.BookModels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _getAllBooksState = MutableStateFlow(GetAllBooksState())
    val getAllBooksState = _getAllBooksState.asStateFlow()

    private val _getAllCategoriesState = MutableStateFlow(GetAllCategoriesState())
    val getAllCategoriesState = _getAllCategoriesState.asStateFlow()

    private val _getBookByCategoryState = MutableStateFlow(GetBookByCategoryState())
    val getBookByCategoryState = _getBookByCategoryState.asStateFlow()

    fun getAllBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllBooks().collect {
                when (it) {
                    is ResultState.Loading -> {
                        _getAllBooksState.value = GetAllBooksState(isLoading = true)
                    }

                    is ResultState.Error -> {
                        _getAllBooksState.value =
                            GetAllBooksState(isLoading = false, error = it.exception)
                    }

                    is ResultState.Success -> {
                        _getAllBooksState.value =
                            GetAllBooksState(isLoading = false, data = it.data)
                    }
                }
            }
        }
    }

    fun getAllCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllCategories().collect {
                when (it) {
                    is ResultState.Loading -> {
                        _getAllCategoriesState.value = GetAllCategoriesState(isLoading = true)
                    }

                    is ResultState.Error -> {
                        _getAllCategoriesState.value =
                            GetAllCategoriesState(isLoading = false, error = it.exception)

                    }

                    is ResultState.Success -> {
                        _getAllCategoriesState.value =
                            GetAllCategoriesState(isLoading = false, data = it.data)
                    }
                }
            }
        }

    }

    fun getBookByCategory(category:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBookByCategory(category).collect{
                when(it){
                    is ResultState.Loading->{
                        _getBookByCategoryState.value= GetBookByCategoryState(isLoading = true)
                    }
                    is ResultState.Error->{}
                    is ResultState.Success->{
                        _getBookByCategoryState.value=
                            GetBookByCategoryState(isLoading = false, data = it.data)
                    }
                }
            }
        }
    }
}

data class GetAllBooksState(
    val isLoading: Boolean = false,
    val data: List<BookModels> = emptyList(),
    val error: Throwable? = null,
)

data class GetAllCategoriesState(
    var isLoading: Boolean = false,
    val data: List<BookCategoryModels> = emptyList(),
    var error: Throwable? = null
)

data class GetBookByCategoryState(
    var isLoading: Boolean = false,
    val data: List<BookModels> = emptyList(),
    var error: Throwable? = null
)