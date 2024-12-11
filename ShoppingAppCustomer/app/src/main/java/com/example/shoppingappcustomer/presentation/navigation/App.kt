package com.example.shoppingappcustomer.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.shoppingappcustomer.presentation.HomeScreenUi
import com.example.shoppingappcustomer.presentation.ProfileScreenUi
import com.example.shoppingappcustomer.presentation.auth.LoginScreenUi
import com.example.shoppingappcustomer.presentation.auth.SignUpScreenUi
import com.example.shoppingappcustomer.presentation.prodctDetails.EachProductDetailUi
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(firebaseAuth: FirebaseAuth) {

    val navController = rememberNavController()

    var selectedItemIndex by remember{ mutableStateOf(0) }

    var currentDestination= navController.currentBackStackEntry?.destination?.route

    var isBottomBarVisible by remember{
        mutableStateOf(true)
    }

    val items= listOf(
        BottomNavItems("Home", Icons.Filled.Home),
        BottomNavItems("WishList", Icons.Filled.HeartBroken),
        BottomNavItems("Cart", Icons.Filled.ShoppingCart),
        BottomNavItems("Profile", Icons.Filled.Person)
    )


    var startScreen = if (firebaseAuth.currentUser == null) {
        SubNavigation.AuthScreen
    } else {
        SubNavigation.MainHomeScreen
    }

    NavHost(navController = navController, startDestination = startScreen) {

        navigation<SubNavigation.AuthScreen>( // this will now act as a sub host
            startDestination = Routes.LoginScreen
        ) {
            composable<Routes.LoginScreen> {
                LoginScreenUi(navController = navController)
            }
            composable<Routes.SignUpScreen> {
                SignUpScreenUi(navController = navController)
            }

        }

        navigation<SubNavigation.MainHomeScreen>(startDestination = Routes.HomeScreen) {

            composable<Routes.HomeScreen> {
                HomeScreenUi(navController = navController)
            }

            composable<Routes.ProfileScreen> {
                ProfileScreenUi(navController = navController)
            }

        }

        composable<Routes.EachProductDetailsScreen> {
            val data=it.toRoute<Routes.EachProductDetailsScreen>()
            EachProductDetailUi(navController = navController, productID = data.productId)
        }
    }
    Scaffold(
        modifier=Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, bottomNavItems ->
                    NavigationBarItem(selected = selectedItemIndex == index, onClick = {
                        selectedItemIndex = index
                    }, icon = {
                        bottomNavItems.icon
                    })
                }
            }
        }
        ) {}
}


data class BottomNavItems(
    val label: String,
    val icon: ImageVector,
)