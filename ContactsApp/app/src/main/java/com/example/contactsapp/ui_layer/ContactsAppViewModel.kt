package com.example.contactsapp.ui_layer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.data_layer.database_table.ContactAppTable
import com.example.contactsapp.data_layer.repository.Repository
import com.example.contactsapp.ui_layer.state.ContactAppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsAppViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val contactList = repository.getAllContact()  // to fetch the list of contacts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    // Add flow for deleted contacts
    private val deletedContacts = repository.getDeletedContacts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state =
        MutableStateFlow(ContactAppState()) // jo data class banai uski state manage karne ke liye

    val state = combine(//combine operator merges two flows: the UI state and contact list
        _state,
        contactList,
        deletedContacts
    ) { //combine both state as we need them together can be used separately but
        // for code modularity we use combine
            _state, contactList, deletedContacts ->
        _state.copy(contactList = contactList, deletedContactList = deletedContacts)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),// keeps the state active for 5 seconds after the last subscriber
        ContactAppState()
    )

    fun upsertContact() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.upsertContact(
                ContactAppTable(
                    id = state.value.id.value,
                    name = state.value.name.value,
                    phone = state.value.phoneNumber.value,
                    email = state.value.email.value,
                    image = state.value.image.value,
                    isFavorite = state.value.isFavorite.value,
                    isDeleted = state.value.isDeleted.value,
                )
            )
        }
    }

    fun deleteContactsPermanently(contactId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val contact = repository.getContactById(contactId).firstOrNull()
            contact?.let {
                repository.deleteContact(it) // Delete the contact from the database
            }

        }
    }

    fun deleteAllDeletedContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            val deletedContacts = repository.getDeletedContacts().firstOrNull()
            deletedContacts?.let { contacts ->
                contacts.forEach { contact ->
                    repository.deleteContact(contact) // Delete each contact
                }
            }
        }
    }

    fun getContactById(contactId: Int): Flow<ContactAppTable> {
        return repository.getContactById(contactId) // This is a flow
    }

    // Update the "Favorite" status
    fun updateFavoriteStatus(contactId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            val contact = repository.getContactById(contactId).firstOrNull() // Get current contact
            contact?.let {
                it.isFavorite = isFavorite
                repository.upsertContact(it) // Update the contact with the new favorite status
            }
        }
    }

    // Update the "Deleted" status
    fun updateDeletedStatus(contactId: Int, isDeleted: Boolean) {
        viewModelScope.launch {
            val contact = repository.getContactById(contactId).firstOrNull()
            contact?.let {
                it.isDeleted = isDeleted
                repository.upsertContact(it)
            }
        }
    }
}