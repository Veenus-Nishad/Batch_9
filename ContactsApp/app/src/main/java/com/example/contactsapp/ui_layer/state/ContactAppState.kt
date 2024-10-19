package com.example.contactsapp.ui_layer.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.contactsapp.data_layer.database_table.ContactAppTable

data class ContactAppState(
    val contactList: List<ContactAppTable> = emptyList(),//mutable isiliye nahi lagaya kyunki flow handle kar leta hai yeh list
    val deletedContactList: List<ContactAppTable> = emptyList(), // New list for deleted contacts
    val id: MutableState<Int?> = mutableStateOf(null),
    val name: MutableState<String> = mutableStateOf(""),
    val phoneNumber: MutableState<String> = mutableStateOf(""),
    val email: MutableState<String> = mutableStateOf(""),
    val image: MutableState<ByteArray?> = mutableStateOf(null),
    var isFavorite: MutableState<Boolean> = mutableStateOf(false),
    var isDeleted: MutableState<Boolean> = mutableStateOf(false)
)
