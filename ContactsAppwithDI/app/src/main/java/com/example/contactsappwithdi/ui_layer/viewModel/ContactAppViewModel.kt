package com.example.contactsappwithdi.ui_layer.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsappwithdi.data.repository.Repository
import com.example.contactsappwithdi.data.tables.Contact
import com.example.contactsappwithdi.ui_layer.state.ContactState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactAppViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val contactList = repository.getAllContact()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state =
        MutableStateFlow(ContactState()) // jo data class banai uski state manage karne ke liye

    val state = combine(
        _state,
        contactList
    ) { //combine both state as we need them together can be used separately but
        // for code modularity we use combine
            _state, contactList ->
        _state.copy(contactList = contactList)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        ContactState()
    )

     fun upsertContact() {
        viewModelScope.launch{
            repository.upsertContact(
                Contact(
                    name = state.value.name.value,
                    phoneNumber = state.value.phoneNumber.value,
                    email = state.value.email.value
                )
            )
        }
    }
}

// Flow ke emission ko receive karenge , Flow emission refers to the process of a Flow producing a new
// value that can be consumed by a subscriber. toh uske liye ek var contactList
/*
    .stateIn() => Collects state flow , lekin tab tak nahi jab tak koi isme changes na lane lage
    .SharingStarted.WhileSubscribed(stopTimeoutMillis = x) => jab jab use hora tab tab collect hoga
    stopTimeOutMillis => x time tak agar state me change nahi hua toh collect karo
*/