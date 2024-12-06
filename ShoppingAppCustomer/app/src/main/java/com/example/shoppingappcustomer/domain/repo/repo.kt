package com.example.shoppingappcustomer.domain.repo

import com.example.shoppingappcustomer.common.ResultState
import com.example.shoppingappcustomer.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface repo {

    fun getAllCategory(): Flow<ResultState<List<Category>>>
}