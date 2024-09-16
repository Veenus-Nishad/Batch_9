package com.example.contactsappwithdi.data.repository

import com.example.contactsappwithdi.data.database.ContactDatabase
import com.example.contactsappwithdi.data.tables.Contact
import kotlinx.coroutines.flow.Flow


// A Custom Class to Call db Function
class Repository(val db_obj: ContactDatabase) {
    suspend fun upsertContact(contact: Contact) {
        db_obj.contactDao().upsertContact(contact)
    }
    suspend fun deleteContact(contact: Contact) {
        db_obj.contactDao().deleteContact(contact)
    }
    fun getAllContact() : Flow<List<Contact>> {
        return db_obj.contactDao().getAllContact()
    }
}