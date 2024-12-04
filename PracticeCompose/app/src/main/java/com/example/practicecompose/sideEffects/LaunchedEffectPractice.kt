package com.example.practicecompose.sideEffects

import android.nfc.Tag
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PauseCircleFilled
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.StopCircle
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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

 enum class CounterState{
        Started,inProgress,Finished
}

suspend fun FetchIcon(counterState: CounterState){
    delay(200)
    when(counterState){
        CounterState.Started-> Icons.Filled.PlayCircleFilled
        CounterState.inProgress-> Icons.Filled.PauseCircleFilled
        CounterState.Finished-> Icons.Filled.StopCircle

    }
}

@Composable
fun StartTimer() {

    var counterState by remember { mutableIntStateOf(0) }

    var isCounterFinished by remember { mutableStateOf(false) }

    var bgColor by remember { mutableStateOf(Color.White) }

    var colors = listOf(
        Color.Blue,
        Color.Gray,
        Color.Red,
        Color.Green,
        Color.Yellow,
        Color.Magenta,
        Color.Cyan,
        Color.Black,
        Color.Blue,

    )

    LaunchedEffect(Unit) { // unit for only one whole execution of forEach Loop agar counter State se karte toh 8 baar call hota Launched effect

         while (counterState < colors.size) {
            delay(1000) // 1-second delay
            bgColor = colors[counterState] // Update background color
            counterState++ // Increment counter

            // If this is the last color, delay to allow display
            if (counterState == colors.size) {
                delay(1000) // Add extra delay for the last color
                isCounterFinished = true // Mark as finished
            }
        }
    }


    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isCounterFinished){
        Text(
            "Counter is Finished"

        )}
        else{
            Text(
            text =  counterState.toString() ,
            modifier =  Modifier.size(60.dp) // Set the size of the text box
                .background(bgColor) // Background color
                .wrapContentSize(Alignment.Center)) // Center the text within the box
        }

    }
}


