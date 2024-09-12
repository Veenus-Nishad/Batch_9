package com.example.contactappwithmvvm.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.contactappwithmvvm.database.tables.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContacts(contacts: List<Contact>) // can delete multiple now

    @Query("SELECT * FROM contact_table")
    fun getAllContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM contact_table WHERE name = :name AND number = :number")
    fun isContactAlreadyExisting(name: String, number: String): List<Contact>

}