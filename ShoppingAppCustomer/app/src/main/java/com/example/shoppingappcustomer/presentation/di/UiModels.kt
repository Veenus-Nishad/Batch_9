package com.example.shoppingappcustomer.presentation.di

import com.example.shoppingappcustomer.data.repoimpl.repoImpl
import com.example.shoppingappcustomer.domain.repo.repo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UiModels {
    @Provides
    fun provideRepo(
        firebaseFirestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth
    ): repo {
        return repoImpl(firebaseFirestore, firebaseAuth)
    }

}