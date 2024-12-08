package com.example.shoppingappcustomer.usecase

import com.example.shoppingappcustomer.domain.repo.repo
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val repo:repo
) {
    fun getAllCategoryUseCase()=repo.getAllCategory()
    fun getCategoryInLimit()=repo.getCategoryInLimit()
}

