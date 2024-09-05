package com.example.contactappwithmvvm.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact (
    @PrimaryKey(autoGenerate = true) var id:Int?=null,
    var name:String,
    var number:String,
    var email:String,
//    var image:ByteArray,
//    var DOB:Long
)