package com.example.contactsappwithroomdatabaseversion1.data.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactsappwithroomdatabaseversion1.data.dao.ContactDao
import com.example.contactsappwithroomdatabaseversion1.data.tables.Contact

@Database(entities =  [Contact::class],version=1) // we use " :: class " to directly pass the class
                                                         // Contact as an entity

/*
1.entities: An array of Class objects representing the entities (tables) in your database.
2.version: The database version number. This is used to track schema changes and trigger migrations when the version changes.
3.exportSchema: (Optional) A boolean indicating whether to export the schema to a JSON file.
4.autoMigrations: (Optional) An array of AutoMigration objects specifying how to automatically migrate the database schema between versions
*/
abstract class ContactDatabase : RoomDatabase() {
    abstract fun dao():ContactDao
    // the dao() function in your ContactDatabase class acts as a bridge between your database and
    // your application code. It provides a way to access the ContactDao interface, which defines the
    // methods for interacting with the Contact entity. By making it abstract, you ensure that any subclass of ContactDatabase must implement this function, providing a consistent way to access the DAO.
}