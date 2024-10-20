package com.example.e_book.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.util.Locale.Category

@Composable
fun BookCategory(){

    Column(modifier=Modifier.fillMaxSize()){
        Text(text="Category")
    }
}