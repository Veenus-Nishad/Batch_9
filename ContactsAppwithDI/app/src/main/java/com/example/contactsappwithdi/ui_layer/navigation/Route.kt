package com.example.contactsappwithdi.ui_layer.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
data class AddEditScreen(val contactId: Int? = null) // Accept an optional contact ID

@Serializable
data class MoreInfoScreen(val contactId: Int)