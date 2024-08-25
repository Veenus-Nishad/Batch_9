package com.example.contactsappwithroomdatabaseversion1.data

import android.content.Context
import androidx.room.Room
import com.example.contactsappwithroomdatabaseversion1.data.dataSource.ContactDatabase

object DatabaseInstance {
    lateinit var db:ContactDatabase
    fun getDB(context:Context):ContactDatabase{
        // jab klass:Class<T> ho tab .java use kare aur jab KClass<*> tab sirf class
       return  Room.databaseBuilder(context,ContactDatabase::class.java, "contact_db").allowMainThreadQueries() .build()
    }
}