package com.example.shoppingapp.domain.models

data class category(
    var name: String = "",
    val date: Long = System.currentTimeMillis(),
    var imageUri: String = ""
)
