package com.example.contactsappwithroomdatabaseversion1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.contactsappwithroomdatabaseversion1.data.DatabaseInstance
import com.example.contactsappwithroomdatabaseversion1.presentation.App
import com.example.contactsappwithroomdatabaseversion1.ui.theme.ContactsAppWithRoomDatabaseVersion1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val dbObject=DatabaseInstance.getDB(this).dao()
            ContactsAppWithRoomDatabaseVersion1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   App(dbObject,modifier=Modifier.padding(innerPadding))
                }
            }
        }
    }
}
