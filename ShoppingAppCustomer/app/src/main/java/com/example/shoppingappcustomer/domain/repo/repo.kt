package com.example.shoppingappcustomer.domain.repo

import com.example.shoppingappcustomer.common.ResultState
import com.example.shoppingappcustomer.domain.models.Category
import com.example.shoppingappcustomer.domain.models.ProductModel
import com.example.shoppingappcustomer.domain.models.UserData
import kotlinx.coroutines.flow.Flow

interface repo {

    fun registerUserWithEmailAndPassword(userData: UserData):Flow<ResultState<String>>
    fun loginUserWithEmailAndPassword(userData: UserData):Flow<ResultState<String>>

    fun getAllCategory(): Flow<ResultState<List<Category>>>
    fun getCategoryInLimit(): Flow<ResultState<List<Category>>>
    fun getProductById():Flow<ResultState<ProductModel>>

    fun getAllProducts():Flow<ResultState<List<ProductModel>>>
    fun getProductsInLimit():Flow<ResultState<List<ProductModel>>>
}