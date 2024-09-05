package com.example.contactappwithmvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactappwithmvvm.database.dao.ContactDao
import com.example.contactappwithmvvm.database.tables.Contact

/*
 jo table bani hai ultimately agar unko access/download karna chahte ho toh exportSchema = true
*/
@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactAppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    // agar kisi class/function ke andar alag se Object banana chahte hai toh we use companion object
    companion object {
        var dbInstance: ContactAppDatabase? = null
        fun getInstance(context: Context): ContactAppDatabase {
            synchronized(this){ // sync and lock kyunki we want that agar isne object le rakha
                // hai toh nobody else will receive a copy of the object
                if (dbInstance == null) {
                    dbInstance =
                        Room.databaseBuilder(context, ContactAppDatabase::class.java, "test").build()
                }
            }

            return dbInstance!! // double exclamation ka matlab hota hai ki error aari hai toh aane do
                                // in my case null pada hai toh null hi dedo
        }
    }
}