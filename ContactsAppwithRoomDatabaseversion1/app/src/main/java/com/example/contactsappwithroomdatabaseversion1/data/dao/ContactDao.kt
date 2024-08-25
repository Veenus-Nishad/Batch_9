package com.example.contactsappwithroomdatabaseversion1.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.contactsappwithroomdatabaseversion1.data.tables.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Upsert // for update and insert
    fun saveUpdateContact(contact:Contact)

    @Delete
    fun deleteContact(contact:Contact)

    @Query("SELECT * FROM contact ") // to read we use Query
    fun getAllContact(): Flow<List<Contact>> // Flow to Auto Refresh the data changes

}