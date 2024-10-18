package com.example.contactsapp.data_layer.repository

import com.example.contactsapp.data_layer.database.ContactAppDatabase
import com.example.contactsapp.data_layer.database_table.ContactAppTable
import kotlinx.coroutines.flow.Flow

class Repository(val database_object: ContactAppDatabase) {
    suspend fun upsertContact(contactAppTable: ContactAppTable) {
        database_object.ContactDao().upsertContact(contactAppTable)
    }

    suspend fun deleteContact(contactAppTable: ContactAppTable) {
        database_object.ContactDao().deleteContact(contactAppTable)
    }

    fun getAllContact(): Flow<List<ContactAppTable>>{
        return database_object.ContactDao().getAllContact()

    }

    fun getContactById(contactId:Int):Flow<ContactAppTable>{
        return database_object.ContactDao().getContactById(contactId)
    }
}