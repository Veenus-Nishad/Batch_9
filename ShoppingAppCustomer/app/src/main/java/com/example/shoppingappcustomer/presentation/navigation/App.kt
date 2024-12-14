package com.example.shoppingappcustomer.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.HeartBroken
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.shoppingappcustomer.presentation.CartScreenUi
import com.example.shoppingappcustomer.presentation.HomeScreenUi
import com.example.shoppingappcustomer.presentation.ProfileScreenUi
import com.example.shoppingappcustomer.presentation.WishlistScreenUi
import com.example.shoppingappcustomer.presentation.auth.LoginScreenUi
import com.example.shoppingappcustomer.presentation.auth.SignUpScreenUi
import com.example.shoppingappcustomer.presentation.prodctDetails.EachProductDetailUi
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(firebaseAuth: FirebaseAuth) {

    val navController = rememberNavController()

    var selectedItemIndex by remember { mutableStateOf(0) }

    val currentDestinationAsState =navController.currentBackStackEntryAsState()
    var currentDestination = currentDestinationAsState.value?.destination?.route

    var isBottomBarVisible = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(currentDestination) {
        isBottomBarVisible.value=when(currentDestination){
            Routes.LoginScreen::class.qualifiedName,Routes.SignUpScreen::class.qualifiedName -> false
            else->true
        }
    }

    val items = listOf(
        BottomNavItems("Home", Icons.Filled.Home, Icons.Outlined.Home),
        BottomNavItems("WishList", Icons.Filled.HeartBroken, Icons.Outlined.HeartBroken),
        BottomNavItems("Cart", Icons.Filled.ShoppingCart, Icons.Outlined.ShoppingCart),
        BottomNavItems("Profile", Icons.Filled.Person, Icons.Outlined.Person)
    )


    var startScreen = if (firebaseAuth.currentUser == null) {
        SubNavigation.AuthScreen
    } else {
        SubNavigation.MainHomeScreen
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { if (isBottomBarVisible.value)NavigationBar {
                items.forEachIndexed { index, bottomNavItems ->
                    NavigationBarItem(selected = selectedItemIndex == index, onClick = {
                        selectedItemIndex = index

                        when (selectedItemIndex) {
                            0 -> navController.navigate(Routes.HomeScreen)
                            1 -> navController.navigate(Routes.WishlistScreen)
                            2 -> navController.navigate(Routes.CartScreen)
                            3 -> navController.navigate(Routes.ProfileScreen)
                        }
                    }, icon = {
                       Icon(imageVector =  if (selectedItemIndex == index) {
                           bottomNavItems.unSelectedIcon
                       } else {
                           bottomNavItems.unSelectedIcon
                       }, contentDescription = null
                       )
                    })
                }
            }
        }
    ) { innerpadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = if (isBottomBarVisible.value) innerpadding.calculateBottomPadding() else 0.dp)
        ) {
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

                //for nav Bar
                navigation<SubNavigation.MainHomeScreen>(startDestination = Routes.HomeScreen) {

                    composable<Routes.HomeScreen> {
                        HomeScreenUi(navController = navController)
                    }

                    composable<Routes.ProfileScreen> {
                        ProfileScreenUi(navController = navController)
                    }

                    composable<Routes.WishlistScreen> {
                        WishlistScreenUi(navController = navController)
                    }

                    composable<Routes.CartScreen> {
                        CartScreenUi(navController = navController)
                    }
                }

                composable<Routes.EachProductDetailsScreen> {
                    val data = it.toRoute<Routes.EachProductDetailsScreen>()
                    EachProductDetailUi(navController = navController, productID = data.productId)
                }
            }
        }
    }
}


data class BottomNavItems(
    val label: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
)