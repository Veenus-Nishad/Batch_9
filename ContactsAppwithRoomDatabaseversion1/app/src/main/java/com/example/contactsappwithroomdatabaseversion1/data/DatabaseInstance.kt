package com.example.contactsappwithroomdatabaseversion1.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactsappwithroomdatabaseversion1.data.dataSource.ContactDatabase

object DatabaseInstance {
    var db: ContactDatabase? = null // ye
    fun getDB(context: Context): ContactDatabase {
        // jab klass:Class<T> ho tab .java use kare aur jab KClass<*> tab sirf class
        if (db == null) {
            db=Room.databaseBuilder(context, ContactDatabase::class.java, "contact_db")
                .allowMainThreadQueries().setJournalMode(RoomDatabase.JournalMode.TRUNCATE).build()

            // .allowMainThreadQueries() taki mainThread pe chal jae kyunki db ke kaam backgroud mein prefer hote hai
            // Vo nahi laga matlab hun log Coroutines ka use kar rahe hai

            /*  db=Room.databaseBuilder(context, ContactDatabase::class.java, "contact_db")
                .allowMainThreadQueries().setJournalMode(RoomDatabase.JournalMode.TRUNCATE).build() */

            // so that jab bhi function call ho naya object na bane
            // as we are returning the Object to naya banega har bar
        }
        return db!!
    }
}