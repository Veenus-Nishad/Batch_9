package com.example.shoppingapp.presentation.di

import com.example.shoppingapp.data.repoimpl.repoImpl
import com.example.shoppingapp.domain.repo.repo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UIModels {
    @Provides
    fun provideRepo(
        fireStoreFirebase:FirebaseFirestore,
        firebaseStorage: FirebaseStorage
    ): repo {
        return repoImpl(fireStoreFirebase,firebaseStorage)
    }
}