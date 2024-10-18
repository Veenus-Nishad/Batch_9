package com.example.contactsapp.ui_layer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.data_layer.database_table.ContactAppTable
import com.example.contactsapp.data_layer.repository.Repository
import com.example.contactsapp.ui_layer.state.ContactAppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsAppViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val contactList = repository.getAllContact()  // to fetch the list of contacts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state =
        MutableStateFlow(ContactAppState()) // jo data class banai uski state manage karne ke liye

    val state = combine(//combine operator merges two flows: the UI state and contact list
        _state,
        contactList
    ) { //combine both state as we need them together can be used separately but
        // for code modularity we use combine
            _state, contactList ->
        _state.copy(contactList = contactList)
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

    fun deleteContactsPermanently(){
        val contact=ContactAppTable(
            id = state.value.id.value,
            name = state.value.name.value,
            phone = state.value.phoneNumber.value,
            email = state.value.email.value,
            image = state.value.image.value,
        )
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteContact(contact)
        }
    }
}