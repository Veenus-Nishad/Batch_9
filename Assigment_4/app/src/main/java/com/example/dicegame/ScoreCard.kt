package com.example.dicegame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Score(){
    Card(modifier = Modifier.padding(16.dp).height(120.dp), shape= RoundedCornerShape(10.dp)) {
        Row(modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center){
            Text(text="Score", fontSize = 26.sp)
        }
        Row(modifier=Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically){
            Text(text="1", fontSize = 30.sp)
            Text(text="2", fontSize = 30.sp)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewNameAndScore(){
    Score()
}