package com.example.shoppingapp.domain.repo

import android.media.Image
import android.net.Uri
import com.example.shoppingapp.common.ResultState
import com.example.shoppingapp.domain.models.ProductModels
import com.example.shoppingapp.domain.models.category
import kotlinx.coroutines.flow.Flow

interface repo {

    suspend fun addCategory(category: category): Flow<ResultState<String>>

    suspend fun getCategories():Flow<ResultState<List<category>>>

    suspend fun addProduct(productsModels: ProductModels):Flow<ResultState<String>>

    suspend fun addImage(image:Uri):Flow<ResultState<String>>

}