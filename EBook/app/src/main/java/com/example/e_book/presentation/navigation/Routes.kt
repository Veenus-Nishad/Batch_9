package com.example.e_book.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    object HomeScreen

    @Serializable
    data class pdfViewer(
        val pdfUrl: String
    )

    @Serializable
    data class BookByCategory(
        val category: String
    )

}
