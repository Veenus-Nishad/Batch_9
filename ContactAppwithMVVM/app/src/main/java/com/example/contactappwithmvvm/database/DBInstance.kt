package com.example.contactappwithmvvm.database

import android.content.Context
import androidx.room.Room
import com.example.contactappwithmvvm.DB_NAME

object DBInstance {

    private var dbInstance: ContactAppDatabase? = null
    fun getInstance(context: Context): ContactAppDatabase {
        synchronized(this) { // sync and lock kyunki we want that agar isne object le rakha
            // hai toh nobody else will receive a copy of the object
            if (dbInstance == null) {
                dbInstance =
                    Room.databaseBuilder(
                        context, ContactAppDatabase::class.java, DB_NAME
                    ).build()
            }
        }
        /*
                              .addTypeConverter()
                Suppose we have a custom datatype or a data type that is kotlin specific for our
                 table but db only understands sql we need to convert it into sql format before for e.g ->
                 val image:Image
                 ).addTypeConverter().build()
         */

        /*
            TO CREATE DATABASE FROM SOMEWHERE/SOME-FILE i.e USING PRE-POPULATED DATA
                createFromFile()  -> Enter String file path
                createFromAsset() -> needs a assets package
                createFromInputStream() -> Purani cheez hai

        */

        /*
            setAutoCloseTimeOut() -> Database instance kitna time ke liye avail hoga
        */

        return dbInstance!!  // double exclamation ka matlab hota hai ki error aari hai toh aane do
        // in my case null pada hai toh null hi dedo
    }
}
