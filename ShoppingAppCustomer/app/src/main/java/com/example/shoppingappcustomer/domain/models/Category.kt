package com.example.shoppingappcustomer.domain.models

data class Category(
    var name: String = "",
    val date: Long = System.currentTimeMillis(),
    var imageUri: String = ""
)
