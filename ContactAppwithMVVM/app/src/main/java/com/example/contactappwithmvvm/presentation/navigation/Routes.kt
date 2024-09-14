package com.example.contactappwithmvvm.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
data class AddEditScreen( var id: Int?=null)

@Serializable
object DeletedContactsScreen

@Serializable
data class MoreContactInformationScreen(var id: Int?=null)


