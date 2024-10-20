package com.example.e_book.di

import com.example.e_book.data.repository.Repository
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    //Firebase Instance
    @Provides
    @Singleton
    fun provideFireBaseRealtimeDatabase():FirebaseDatabase{
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideRepository(firebaseDatabase: FirebaseDatabase): Repository {
        return Repository(firebaseDatabase)

    }
}