package com.example.shoppingapp.domain.repo

import com.example.shoppingapp.common.ResultState
import com.example.shoppingapp.domain.models.category
import kotlinx.coroutines.flow.Flow

interface repo {

    fun addCategory(category: category): Flow<ResultState<String>>

}