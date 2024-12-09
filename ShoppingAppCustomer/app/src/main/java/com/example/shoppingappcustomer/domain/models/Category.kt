package com.example.shoppingappcustomer.domain.models

data class Category(
    var categoryId : String = "",
    var name: String = "",
    val date: Long = System.currentTimeMillis(),
    var imageUri: String = ""
)
