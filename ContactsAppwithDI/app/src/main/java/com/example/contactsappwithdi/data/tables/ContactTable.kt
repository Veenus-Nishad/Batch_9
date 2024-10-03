package com.example.contactsappwithdi.data.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val name: String,
    val phoneNumber: String,
    val email: String,
    val DOB:Long?=null,
    val image:ByteArray?=null,
    val isFavorite: Boolean? = false,
    val isDeleted:Boolean?=false
)


// Blob ka sql equivalent hai ByteArray