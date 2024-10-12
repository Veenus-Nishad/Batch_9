package com.example.e_book.ui_layer.tabSetUp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun TabLayout(modifier: Modifier = Modifier) {
    val pagerState = remember{
        mutableStateOf(0)
    }

    Tabs(pagerState)
   /// TabsContent()
}

