package com.example.shoppingapp.domain.models

data class ProductModels(
    val name: String = "",
    val description: String = "",
    val price: String = "",
    val finalprice: String = "",
    val category: String = "",
    val image: String = "",
    val date: Long = System.currentTimeMillis(),
    val availableUnits: Int = 0,
    val createdBy: String = ""
)