package com.example.e_book.presentation

  import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.e_book.presentation.navigation.Routes
import com.example.e_book.presentation.viewModel.AppViewModel

@Composable
fun AllBooksScreen(
    viewModel: AppViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.getAllBooksState.collectAsState()
    val data = state.value.data ?: emptyList()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllBooks()
    }

    when {
        state.value.isLoading -> {
        }

        state.value.error != null -> {}
        state.value.data.isNotEmpty() -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(data) {
                    //Log.d("TAG", "Data : ${data.toString()}")
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

@Composable
fun Books(
    title: String,
    bookImage: String,
    url: String,
    author: String,
    category: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable{
                onClick()
            }
    ) {
        Column {
            TextOverImage(
                title = title,
                bookImage = bookImage
            )

        }


    }
}