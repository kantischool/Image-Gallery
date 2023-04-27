package com.example.imagegallery

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.imagegallery.ui.theme.ImageGalleryTheme
import com.example.imagegallery.widgets.ImageList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val imgViewModel by viewModels<ImageViewModal>()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // imgViewModel.getImages()
        setContent {
            ImageGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController =  rememberNavController()
                    val drawerState = rememberDrawerState(DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    val openDrawer = {
                        scope.launch {
                            drawerState.open()
                        }
                    }

                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            NavDrawer(
                                onDestinationClicked = { route ->
                                    scope.launch {
                                        drawerState.close()
                                    }
                                    navController.navigate(route) {
                                       // navController.graph.startDestinationRoute?.let { popUpTo(it) }
                                    }
                                }
                            )
                        }
                    ) {
                        NavigationGraph(navController = navController, viewModal = imgViewModel, openDrawer)
                    }
                  //  ImageList(imgViewModel)
//                    LazyVerticalGrid(columns =GridCells.Adaptive(minSize = 100.dp)) {
//                        items(imgaes) { item ->
//                            SoloImage(data = item)
//                        }
//                    }

                }
            }
        }
    }
}