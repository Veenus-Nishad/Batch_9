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
    @Provides
    fun provideUserPreferenceManager(@ApplicationContext context: Context)= UserPreferencesManager(context)

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context)=context
}