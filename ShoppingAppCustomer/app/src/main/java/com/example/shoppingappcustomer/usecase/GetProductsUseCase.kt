package com.example.shoppingappcustomer.usecase

import com.example.shoppingappcustomer.domain.repo.repo
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repo: repo
) {
    fun getAllProducts() = repo.getAllProducts()
    fun getProductsInLimit()=repo.getProductsInLimit()
}