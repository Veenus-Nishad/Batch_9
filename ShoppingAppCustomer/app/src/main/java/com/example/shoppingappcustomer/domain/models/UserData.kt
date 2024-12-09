package com.example.shoppingappcustomer.domain.models

data class UserData (
    val firstName:String="",
    val lastName:String="",
    val email:String="",
    val password:String="",
    val confirmPassword:String="",
    val phoneNumber:String="",
    val address:String="",
    val userImage:String="",
    var uuid : String = "",
)