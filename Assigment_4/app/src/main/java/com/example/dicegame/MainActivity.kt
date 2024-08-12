package com.example.dicegame


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dicegame.ui.theme.DiceGameTheme
import java.lang.Math.random
import kotlin.math.absoluteValue

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
fun GameApp(modifier: Modifier) {
    var playerTurn = remember {
        mutableStateOf(true)
    }
    var player1Score = remember {
        mutableStateOf(0)
    }
    var player2Score = remember {
        mutableStateOf(0)
    }
    var result = remember { mutableStateOf(-1) }
    val currentImage = when (result.value) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(top=10.dp)
    ) {
        Score(
            player1Score = player1Score.value,
            player2Score = player2Score.value
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp), horizontalArrangement = Arrangement.SpaceAround) {
            Button(
                onClick = {},
                enabled = if (playerTurn.value) true else false,
            ) {
                Text(text = "Player 1")

            }
            Button(onClick = {}, enabled = if (playerTurn.value) false else true) {
                Text(text = "Player 2")
            }
        }
        if(player1Score.value>=20 || player2Score.value>=20){
            
        }
        else{
            if (result.value < 0) Text(
                text = "Roll the dice to get Started ",
                fontSize = 22.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 120.dp),
                textAlign = TextAlign.Center
            ) else Image(
                painter = painterResource(id = currentImage),
                contentDescription = "Dice Image"
            )
            Button(onClick = {
                result.value = (1..6).random()
                if (playerTurn.value) player1Score.value += result.value else player2Score.value += result.value
                playerTurn.value = !playerTurn.value
            }) {
                Text(text = "Roll")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                result.value = -1
                playerTurn.value = true
                player1Score.value = 0
                player2Score.value = 0
            }) {
                Text(text = "Reset Game")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    GameApp(modifier = Modifier)
}