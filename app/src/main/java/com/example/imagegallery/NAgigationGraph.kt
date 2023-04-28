package com.example.imagegallery

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.Job

@Composable
fun NavigationGraph(
    navController: NavHostController,
    viewModal: ImageViewModal,
    openDrawer: () -> Job,
    ctx: Context
) {
    NavHost(navController, startDestination = DrawerScreens.Home.route) {
        composable(DrawerScreens.Home.route) {
            HomeScreen(viewModal, openDrawer)
        }
        composable(DrawerScreens.Search.route) {
            SearchScreen(viewModal, ctx)
        }
    }
}