package com.example.contactappwithmvvm.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactappwithmvvm.database.ContactAppDatabase
import com.example.contactappwithmvvm.database.DBInstance
import com.example.contactappwithmvvm.database.tables.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/*
    As ab humari db se baat viewModel karega toh uske liye hume db ka obj ya instance yaha lena hoga and our db instance
    requires a context

    ViewModel is lifecycle aware
    object intialized stay for the lifecycle
*/
class ContactAppViewModel(
    val context: Context
) : ViewModel() {
    // var for db object
    var db: ContactAppDatabase

    init { // varna var by default hi create hojaega i.e the database instance is created only when it's actually needed.
        db = DBInstance.getInstance(context)
    }

    fun addUpdateContact(contact: Contact) {   // iske advanced tarike bhi hai
        viewModelScope.launch {
            db.contactDao().upsertContact(contact)
        }
    }

    fun deleteContactsPermanently(contact:List<Contact>){
        viewModelScope.launch {
            db.contactDao().deleteContactsPermanently(contact)
        }
    }

    // Function to mark contact as deleted
    fun deleteContact(contactId: Int) {
        viewModelScope.launch {
            db.contactDao().deleteContact(contactId)
        }
    }

    // Function to restore a deleted contact
    fun restoreContact(contactId: Int) {
        viewModelScope.launch {
            db.contactDao().restoreContact(contactId)
        }
    }

    fun getContactById(contactId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            db.contactDao().getContactById(contactId)
        }

    }

    fun isContactAlreadyExisting(name: String, number: String): StateFlow<Boolean> {
        val stateFlow = MutableStateFlow(false)
        viewModelScope.launch(Dispatchers.IO) {
            stateFlow.value = db.contactDao().isContactAlreadyExisting(name, number).isNotEmpty()
        }
        return stateFlow
    }

    /*
        READ KARNE KE LIYE FUNCTIONS NAHI BANTE HAI AS FLOW AUTOMATICALLY NAYA DATA DE DETA HAI BUT
        USKE LIYE BHI EVENT AUR STATE BANANE PADTE HAI
    */
}