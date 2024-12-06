package com.example.shoppingapp.common

sealed class ResultState<out T>{
    data class Success<T>(val data:String ):ResultState<T>()
    data class Error(val error:String ):ResultState<Nothing>()
    object Loading:ResultState<Nothing>()
}