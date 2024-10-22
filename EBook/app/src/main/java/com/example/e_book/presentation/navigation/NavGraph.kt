package com.example.e_book.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.e_book.presentation.BookByCategoryScreen
import com.example.e_book.presentation.TabBar
import com.example.e_book.presentation.viewModel.pdfViewerUI

@Composable
fun NavGraph(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HomeScreen) {

        composable<Routes.HomeScreen>{
            TabBar(navController)
        }

        composable<Routes.pdfViewer> {
            val data = it.toRoute<Routes.pdfViewer>()
            pdfViewerUI(pdfUrl = data.pdfUrl)
        }

        composable<Routes.BookByCategory> {
            val data = it.toRoute<Routes.BookByCategory>()
            BookByCategoryScreen(navController = navController,category = data.category)
        }
    }
}