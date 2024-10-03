package com.example.contactsappwithdi.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.contactsappwithdi.data.tables.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contact_table WHERE isDeleted=0")
    fun getAllContact(): Flow<List<Contact>>

    @Query("SELECT * FROM contact_table WHERE isFavorite = 1")
    fun favoriteContacts():Flow<List<Contact>>

    @Query("SELECT * FROM contact_table WHERE isDeleted = 1")
    fun deletedContacts():Flow<List<Contact>>

}