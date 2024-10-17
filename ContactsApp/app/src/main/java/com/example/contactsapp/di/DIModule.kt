package com.example.contactsapp.di

import android.app.Application
import androidx.room.Room
import com.example.contactsapp.data_layer.DB_NAME
import com.example.contactsapp.data_layer.database.ContactAppDatabase
import com.example.contactsapp.data_layer.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DIModule {

    @Provides
    @Singleton
    fun provideDatabase(applicationContext: Application): ContactAppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            ContactAppDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideContactRepository(contactAppDatabase: ContactAppDatabase): Repository {
        return Repository(contactAppDatabase)
    }

}