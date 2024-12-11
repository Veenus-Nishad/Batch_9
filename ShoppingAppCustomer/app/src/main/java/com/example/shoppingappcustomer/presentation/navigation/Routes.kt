package com.example.shoppingappcustomer.presentation.navigation

import kotlinx.serialization.Serializable

sealed class SubNavigation {

    /*
    Used to bind Routes that are similar in functionality aur aim to navigate to.
    * */
    @Serializable
    object MainHomeScreen : SubNavigation()

    @Serializable
    object AuthScreen : SubNavigation()
}

sealed class Routes {
    @Serializable
    object LoginScreen

    @Serializable
    object SignUpScreen

    @Serializable
    object CheckOutScreen

    @Serializable
    object ProfileScreen

    @Serializable
    object WishlistScreen

    @Serializable
    object CartScreen

    @Serializable
    object HomeScreen

    @Serializable
    object PaymentScreen

    @Serializable
    object SeeAllProductScreen

    @Serializable
    data class EachProductDetailsScreen(val productId:String)

    @Serializable
    object AllCategoryScreen

    @Serializable
    object EachCategoryItemScreen
}