package com.example.contactappwithmvvm.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact (
    @PrimaryKey(autoGenerate = true) var id:Int?=null,
    var name:String,
    @ColumnInfo(name="number")var number:String,
    var email:String,
    val isDeleted: Boolean = false
//    var image:ByteArray,
//    var DOB:Long
)