package com.example.practicecompose.sideEffects

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

@SuppressLint("UnrememberedMutableState")
@Composable
fun StartTimer() {

    var isTimerComplete by remember { mutableStateOf(false) }

    var countup by remember { mutableIntStateOf(0) }
    var color = listOf(
        Color.Blue,
        Color.Red,
        Color.Gray,
        Color.Green,
        Color.Black,
    )
    var lastIndex=color.lastIndex
    var bgcolor by remember { mutableStateOf(Color.White)}
    LaunchedEffect(true) {
            color.forEach {
                delay(1000)
                bgcolor=it
                Log.d("Tag",bgcolor.toString())
                if (it==color[lastIndex]) {
                    isTimerComplete = true
                } else{countup++}
            }
          }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = bgcolor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {if(isTimerComplete)Text("My countdown is over ")  else Text("${countup}")
    }
}


