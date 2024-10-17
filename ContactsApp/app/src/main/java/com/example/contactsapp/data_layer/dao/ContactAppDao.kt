package com.example.contactsapp.data_layer.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.contactsapp.data_layer.database_table.ContactAppTable
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactAppDao {
    @Upsert
    suspend fun upsertContact(contactAppTable: ContactAppTable)

    @Delete
    suspend fun deleteContact(contactAppTable: ContactAppTable)

    @Query("SELECT * FROM contacts_table WHERE isDeleted=0")
    fun getAllContact(): Flow<List<ContactAppTable>>

    @Query("SELECT * FROM contacts_table WHERE isFavorite = 1")
    fun favoriteContacts(): Flow<List<ContactAppTable>>

    @Query("SELECT * FROM contacts_table WHERE isDeleted = 1")
    fun deletedContacts(): Flow<List<ContactAppTable>>
}