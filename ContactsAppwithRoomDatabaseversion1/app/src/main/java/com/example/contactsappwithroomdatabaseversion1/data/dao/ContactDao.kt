package com.example.contactsappwithroomdatabaseversion1.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.contactsappwithroomdatabaseversion1.data.tables.Contact

@Dao
interface ContactDao {

    @Upsert // for update and insert
    fun saveUpdateContact(contact:Contact)

    @Delete
    fun deleteContact(contact:Contact)

    @Query("SELECT * FROM contact ") // to read we use Query
    fun getAllContact(): List<Contact>

    @Query("SELECT * FROM contact WHERE name LIKE '%' || :name || '%' OR phNo LIKE '%' || :number || '%' ")
    fun isContactAlreadyExisting(name:String,number:String) : List<Contact>

}