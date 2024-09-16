package com.example.contactsappwithdi.data.dao

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.contactsappwithdi.data.tables.Contact
import kotlinx.coroutines.flow.Flow

interface ContactDao {
    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contact_table")
    fun getAllContact(): Flow<List<Contact>>
}