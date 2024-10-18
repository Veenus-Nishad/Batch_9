package com.example.contactsapp.data_layer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactsapp.data_layer.dao.ContactAppDao
import com.example.contactsapp.data_layer.database_table.ContactAppTable

@Database(entities = [ContactAppTable::class], version = 1, exportSchema = false)
abstract class ContactAppDatabase :RoomDatabase(){
    abstract fun ContactDao(): ContactAppDao
}