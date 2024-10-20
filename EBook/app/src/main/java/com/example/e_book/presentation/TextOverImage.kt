package com.example.e_book.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_book.R

@Preview(showSystemUi = true)
@Composable
fun TextOverImage(){

        Card(modifier=Modifier.fillMaxWidth().padding(10.dp)) {
            Box(modifier= Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            Image(painter = painterResource(id = R.drawable.image), alpha = 0.2F, contentDescription = null)
            Text(text = "Ye lo Assignment", fontSize = 24.sp, color = Color.Black)
        }
    }

}