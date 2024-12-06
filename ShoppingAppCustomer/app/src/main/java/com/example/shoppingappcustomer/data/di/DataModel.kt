package com.example.shoppingappcustomer.data.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModel {

    @Provides
    fun provideFirebaseFirestore():FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

}