package com.example.e_book.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.e_book.presentation.navigation.Routes
import com.example.e_book.presentation.viewModel.AppViewModel

@Composable
fun BookByCategoryScreen(
    viewModel: AppViewModel = hiltViewModel(),
    navController: NavController,
    category:String
){
    val state = viewModel.getBookByCategoryState.collectAsState()
    val data = state.value.data ?: emptyList()

    LaunchedEffect(key1 = Unit){
        viewModel.getBookByCategory(category)
    }

    when{
        state.value.isLoading->{
            Box(modifier= Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
               CircularProgressIndicator()
            }
        }
        state.value.error!=null->{
            Box(modifier= Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "Error : ${state.value.error.toString()}")
            }

        }
        state.value.data.isNotEmpty()->{
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(data){
                    Books(
                        title = it.bookName,
                        bookImage = it.bookImage,
                        url = it.bookUrl,
                        author = it.bookAuthor,
                        category = it.bookCategory,
                        onClick = {
                            navController.navigate(Routes.pdfViewer(it.bookUrl))
                        }
                    )
                }
            }
        }
    }
}