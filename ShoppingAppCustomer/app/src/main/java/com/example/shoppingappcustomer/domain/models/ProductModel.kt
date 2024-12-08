package com.example.shoppingappcustomer.domain.models

data class ProductModel(
    val name:String="",
    val description:String="",
    val price:String="",
    val finalprice:String="",
    val category:String="",
    val image:String="",
    val date:Long=System.currentTimeMillis(),
    val availableUnits : Int=0,
    val createdBy:String="",
    val productId:String=""
)
