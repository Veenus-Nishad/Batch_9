package com.example.dependencyinjection.practice

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    fun provideEngine(): Engine {
        return Engine()

    }
}