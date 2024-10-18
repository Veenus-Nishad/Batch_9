package com.example.contactsapp.ui_layer.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
data class AddEditScreen(val contactId:Int?=null)

@Serializable
object RecycleBinScreen

@Serializable
data class MoreInformationScreen(val contactId:Int)