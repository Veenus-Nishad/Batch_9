package com.example.contactsappwithdi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactsappwithdi.data.dao.ContactDao
import com.example.contactsappwithdi.data.tables.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase: RoomDatabase() {
    abstract fun contactDao(): ContactDao

}