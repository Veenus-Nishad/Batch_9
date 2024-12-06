package com.example.shoppingapp.data.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModels {

    //creating firebase object
    @Provides
    fun provideFireStore():FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }
}