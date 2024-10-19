package com.example.contactsapp.data_layer.database_table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class ContactAppTable(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val name: String,
    val phone: String,
    val email: String,
    val image: ByteArray? = null,
    var isFavorite: Boolean? = false,
    var isDeleted: Boolean? = false
)
