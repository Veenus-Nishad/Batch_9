package com.example.barcodescanner.draggable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp

@Preview(showSystemUi = true)
@Composable
fun Preview(modifier: Modifier = Modifier) {
    MainScreen { MainView() }

}

@Composable
fun MainView() {

    val screenWidth = LocalConfiguration.current.screenWidthDp
    val boxSize = Dp(screenWidth / 6f)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        DraggableView {
            Box(modifier = Modifier.size(boxSize)) {
                Text(text = "Hello")
            }
        }
    }

}