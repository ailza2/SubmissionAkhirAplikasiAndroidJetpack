package com.dicoding.submissionakhiraplikasiandroidjetpack.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object About : Screen("about")
    object DetailFruit : Screen("home/{fruitId}") {
        fun createRoute(fruitId: Long) = "home/$fruitId"
    }
}
