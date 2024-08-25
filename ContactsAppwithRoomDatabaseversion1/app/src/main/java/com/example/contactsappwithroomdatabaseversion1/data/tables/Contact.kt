package com.example.contactsappwithroomdatabaseversion1.data.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact") // this will be the name of table inside database
data class Contact(
    @PrimaryKey(autoGenerate = true) /*@ColumnInfo(name="id") */var Id: Int? = null,
    //name inside database set krne ke liye
    var name: String,
    var phNo: String,
    var email: String
)