package com.example.loginsignupwithnavigation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginsignupwithnavigation.screens.Login
import com.example.loginsignupwithnavigation.screens.SignUp

@Composable
fun App(modifier:Modifier=Modifier){
    val navController= rememberNavController()
    
NavHost(navController = navController, startDestination = LoginScreen ) {
    composable<LoginScreen> {
        Login(navController)
    }
    composable<SignUpScreen> {
        SignUp(navController)
    }
}
}
