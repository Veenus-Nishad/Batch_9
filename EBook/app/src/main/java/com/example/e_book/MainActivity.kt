package com.example.e_book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.e_book.ui.theme.EBookTheme
import com.example.e_book.ui_layer.tabSetUp.TabLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EBookTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TabLayout(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

