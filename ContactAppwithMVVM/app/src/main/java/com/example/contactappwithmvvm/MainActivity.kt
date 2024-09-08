package com.example.contactappwithmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contactappwithmvvm.presentation.navigation.AppNavigation
import com.example.contactappwithmvvm.ui.theme.ContactAppWithMVVMTheme
import com.example.contactappwithmvvm.viewmodel.ContactAppViewModel

class MainActivity : ComponentActivity() {
    val viewModel by viewModels<ContactAppViewModel>(
        factoryProducer ={
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ContactAppViewModel(this@MainActivity) as T
                }
            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactAppWithMVVMTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation(viewModel = viewModel )

                }
            }
        }
    }
}

