package com.example.contactsappwithdi.ui_layer.viewModel

import androidx.lifecycle.ViewModel
import com.example.contactsappwithdi.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactAppViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

}