package com.example.medicalstorevendor.ui_layer.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medicalstorevendor.ui_layer.screen.HomeScreenUI
import com.example.medicalstorevendor.ui_layer.screen.LoginScreenUI
import com.example.medicalstorevendor.ui_layer.screen.PlaceOrderScreenUI
import com.example.medicalstorevendor.ui_layer.screen.SignUpScreenUI
import com.example.medicalstorevendor.user_preferences.UserPreferencesManager

@Composable
fun AppNavigation(
    userPreferencesManager: UserPreferencesManager // kind of like injecting
) {
    val navController = rememberNavController()

    val userId by userPreferencesManager.getUserId.collectAsState(initial = null) // using this to check if there is a stored preference

    var selectedIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(userId) {
        if (userId != null) {
            navController.navigate(HomeScreen)
        } else {
            navController.navigate(LoginScreen)
        }
    }

    val bottomNavigationItems = listOf(
        BottomNavigationItem(
            name = "",
            icon = Icons.Default.Home
        ),
        BottomNavigationItem(
            name = "",
            icon = Icons.Default.Home
        ),
        BottomNavigationItem(
            name = "",
            icon = Icons.Default.Home
        )
    )
    Box() {
        Scaffold(bottomBar = {
            NavigationBar {
                bottomNavigationItems.forEachIndexed { index, bottomNavigationItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = { Icon(bottomNavigationItem.icon, contentDescription = null) }
                    )
                }
            }
        }) { innerPadding ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {
                when (selectedIndex) {
                    0 -> {
                        HomeScreenUI(navController)
                    }

                    1 -> {
                        PlaceOrderScreenUI()
                    }

                    2 -> {
                        SignUpScreenUI(navController)
                    }
                }
            }
            NavHost(navController = navController, startDestination = LoginScreen) {

                composable<SignUpScreen> {
                    SignUpScreenUI(navController)
                }
                composable<LoginScreen> {
                    LoginScreenUI(navController)
                }
                composable<HomeScreen> {
                    HomeScreenUI(navController)
                }
                composable<PlaceOrderScreen> {
                    PlaceOrderScreenUI()
                }


            }
        }
    }

}

data class BottomNavigationItem(
    val name: String,
    val icon: ImageVector
)