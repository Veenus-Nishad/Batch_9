package com.example.e_book.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun TextOverImage(
    title:String,
    bookImage:String,
){

        Card(modifier=Modifier.fillMaxWidth()) {
            Box(modifier= Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
           AsyncImage(model=bookImage, contentDescription = title, alpha = 0.2F)
            Text(text = title, fontSize = 24.sp, color = Color.Black)
        }
    }

}