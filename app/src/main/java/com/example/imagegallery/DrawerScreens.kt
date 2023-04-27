package com.example.imagegallery

sealed class DrawerScreens(val title: String, val route: String) {
    object Home : DrawerScreens("Home", "home")
    object Search : DrawerScreens("Search Image", "search")
}
