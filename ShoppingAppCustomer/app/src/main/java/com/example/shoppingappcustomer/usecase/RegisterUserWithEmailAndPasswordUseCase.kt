package com.example.shoppingappcustomer.usecase

import com.example.shoppingappcustomer.domain.models.UserData
import com.example.shoppingappcustomer.domain.repo.repo
import javax.inject.Inject

class AuthUserWithEmailAndPasswordUseCase @Inject constructor(
    private val repo: repo
){
    fun registerUserWithEmailAndPassword(userData: UserData) = repo.registerUserWithEmailAndPassword(userData)
    fun loginUserWithEmailAndPassword(userData: UserData) = repo.loginUserWithEmailAndPassword(userData)
}