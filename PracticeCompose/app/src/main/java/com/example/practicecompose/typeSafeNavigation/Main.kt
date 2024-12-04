package com.example.practicecompose.typeSafeNavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.practicecompose.ui.theme.PracticeComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeComposeTheme {
                val navController = rememberNavController()

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    bottomBar = {
                        BottomAppBar {
                            BottomNavigation.entries
                                .forEachIndexed { index, navigationItem ->

                                    val isSelected by remember(currentRoute) {
                                        ...
                                    }

                                    NavigationBarItem(
                                        selected = isSelected,
                                        label = { Text(navigationItem.label) },
                                        icon = {
                                            Icon(
                                                navigationItem.icon,
                                                contentDescription = navigationItem.label
                                            )
                                        },
                                        onClick = {
                                            navController.navigate(navigationItem.route)
                                        }
                                    )
                                }
                        }
                    }
                ) {
                    NavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        navController = navController,
                        startDestination = Destinations.HomeGraph
                    ) {
                        navigation<Destinations.HomeGraph>(
                            startDestination = Destinations.Home,
                        ) {

                            composable<Destinations.Home> {
                                Greeting("HomeScreen")
                            }

                            composable<Destinations.Search> {
                                Greeting("Search")
                            }

                            composable<Destinations.Profile> { backStackEntry ->
                                Greeting("Profile")
                            }
                        }
                    }
                }
            }
        }
    }
}

