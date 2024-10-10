package com.example.medicalstorevendor

sealed class State<T> {
    data class Success<T>(val data: T) : State<T>()
    data class Error<T>(val message: String) : State<T>()
    class Loading<T> : State<Nothing>()
}

