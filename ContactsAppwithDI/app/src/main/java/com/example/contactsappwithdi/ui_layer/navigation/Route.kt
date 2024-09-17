package com.example.contactsappwithdi.ui_layer.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
data class AddEditScreen(val id: Int? = null)