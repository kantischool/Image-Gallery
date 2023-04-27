package com.example.imagegallery

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.imagegallery.widgets.ImageList
import kotlinx.coroutines.Job


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModal: ImageViewModal, openDrawer: () -> Job){
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Images", fontSize = 22.sp, fontWeight = FontWeight.Bold
            )
        }, navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description",
                    modifier = Modifier.clickable {
                        openDrawer
                    }
                )
            }
        },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.White)
        )
    }){
        ImageList(viewModel = viewModal)
    }
}