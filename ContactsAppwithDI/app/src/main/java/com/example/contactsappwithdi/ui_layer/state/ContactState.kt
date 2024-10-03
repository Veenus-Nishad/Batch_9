package com.example.contactsappwithdi.ui_layer.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.contactsappwithdi.data.tables.Contact

data class ContactState(
    val contactList: List<Contact> = emptyList(),//mutable isiliye nahi lagya kyunki flow handle kar leta hai yeh list
    val id: MutableState<Int?> = mutableStateOf(null),
    val name: MutableState<String> = mutableStateOf(""),
    val phoneNumber: MutableState<String> = mutableStateOf(""),
    val email: MutableState<String> = mutableStateOf(""),
    val dob: MutableState<Long?> = mutableStateOf(0),
    val image: MutableState<ByteArray?> = mutableStateOf(null),
    var isFavorite: MutableState<Boolean> = mutableStateOf(false),
    var isDeleted:MutableState<Boolean> = mutableStateOf(false)
)


/*
    jitne bhi variable jinki state mein change hona hoga uske liye seperate data class bnaenge
*/