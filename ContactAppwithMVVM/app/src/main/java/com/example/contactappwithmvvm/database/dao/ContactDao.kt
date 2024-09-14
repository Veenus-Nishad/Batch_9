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
    suspend fun deleteContactsPermanently(contacts: List<Contact>) // can delete multiple now

    @Query("SELECT * FROM contact_table")
    fun getAllContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM contact_table WHERE name = :name AND number = :number")
    fun isContactAlreadyExisting(name: String, number: String): List<Contact>

    // Function to get all deleted contact_table
    @Query("SELECT * FROM contact_table WHERE isDeleted = 1")
    fun getDeletedContacts(): Flow<List<Contact>>

    // Function to update the isDeleted status of a contact
    @Query("UPDATE contact_table SET isDeleted = 1 WHERE id = :contactId")
    suspend fun deleteContact(contactId: Int)

    // Function to restore a deleted contact
    @Query("UPDATE contact_table SET isDeleted = 0 WHERE id = :contactId")
    suspend fun restoreContact(contactId: Int)

    @Query("SELECT number FROM contact_table WHERE id = :contactId LIMIT 1")
    fun getContactNumberById(contactId: Int): Flow<String>

}