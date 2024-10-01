package com.example.camerax

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.camerax.ui.theme.CameraXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CameraXTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Permission(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Permission(modifier: Modifier = Modifier) {
    val permissions = listOf(android.Manifest.permission.CAMERA)
    val isGranted = remember {
        mutableStateOf(false)
    }
    val launch = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { isGranted.value = true })
    val context= LocalContext.current
    if(isGranted.value){
        CameraScreen()
    }else{
         Column {
             Button(onClick = {launch.launch(permissions.toTypedArray()) }) {
                 Text(text = "Request Permission")
             }
             val intent=Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Veenus-Nishad"))
             context.startActivity(intent)
         }
    }
}