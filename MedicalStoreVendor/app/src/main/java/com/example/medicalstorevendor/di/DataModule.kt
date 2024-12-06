package com.example.medicalstorevendor.di

import android.content.Context
import com.example.medicalstorevendor.repository.Repository
import com.example.medicalstorevendor.user_preferences.UserPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideRepository() = Repository()

    @Singleton
    @Provides // as iska object baar baar banta isiliye isko di mein dala
    fun provideUserPreferenceManager(@ApplicationContext context: Context) =
        UserPreferencesManager(context)
    // context pass kiya hain to ab local context banana nahi padega yahi wala context use kar sakte hain

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context) = context
}