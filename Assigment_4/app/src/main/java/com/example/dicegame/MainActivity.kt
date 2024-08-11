package com.example.dicegame

import android.app.GameState
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dicegame.ui.theme.DiceGameTheme
import java.lang.Math.random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GameApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GameApp(modifier: Modifier){
    var result by remember { mutableStateOf(1) }
    val currentImage=when(result){
        1->R.drawable.dice_1
        2->R.drawable.dice_2
        3->R.drawable.dice_3
        4->R.drawable.dice_4
        5->R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Score()
        Row(modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceAround) {
            Button(onClick = {}) {
                Text(text = "Player 1")
            }
            Button(onClick = {}) {
                Text(text = "Player 2")
            }
        }
        Image(painter = painterResource(id =currentImage), contentDescription ="Dice Image" )
        Button(onClick = {
            result=(1..6).random()
        }) {
            Text(text = "Roll")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Reset Game")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    GameApp(modifier=Modifier)
}