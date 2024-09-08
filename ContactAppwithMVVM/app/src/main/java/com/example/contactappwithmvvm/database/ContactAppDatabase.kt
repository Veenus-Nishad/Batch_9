package com.example.contactappwithmvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactappwithmvvm.DB_NAME
import com.example.contactappwithmvvm.database.dao.ContactDao
import com.example.contactappwithmvvm.database.tables.Contact

/*
 jo table bani hai ultimately agar unko access/download karna chahte ho toh exportSchema = true
*/
@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactAppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    // agar kisi class/function ke andar alag se Object banana chahte hai toh we use companion object

}