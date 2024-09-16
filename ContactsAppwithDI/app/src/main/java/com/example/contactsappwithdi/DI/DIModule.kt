package com.example.contactsappwithdi.DI

import android.app.Application
import androidx.room.Room
import com.example.contactsappwithdi.DB_NAME
import com.example.contactsappwithdi.data.database.ContactDatabase
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
    fun provideContactDatabase(application: Application): ContactDatabase {
        return Room.databaseBuilder(
            application,
            ContactDatabase::class.java,
            DB_NAME
        ).build()
    }
}