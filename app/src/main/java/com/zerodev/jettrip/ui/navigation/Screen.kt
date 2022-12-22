package com.zerodev.jettrip.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object Detail : Screen("home/{id}") {
        fun createRoute(id: Long) = "home/$id"
    }
}
