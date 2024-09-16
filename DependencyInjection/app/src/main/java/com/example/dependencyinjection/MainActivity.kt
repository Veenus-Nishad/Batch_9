package com.example.dependencyinjection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.example.dependencyinjection.ui.theme.DependencyInjectionTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint // Field injection keval aur keval android entry point me lagta hai i.e isi class mein chalega
class MainActivity : ComponentActivity() {
//    @Inject
//    lateinit var engine: Engine
    //Field Injection , Car ka Object ban raha hai
    @Inject lateinit var car: Car // hilt ab module mein dekhega kon provide kara rha hai car
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DependencyInjectionTheme {
                Surface {

                }
                car.StartCar()
                }
            }
        }
    }

