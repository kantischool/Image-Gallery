package com.example.imagegallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.example.imagegallery.modals.SinglePhotoX
import com.example.imagegallery.ui.theme.ImageGalleryTheme
import com.example.imagegallery.widgets.ImageList
import com.example.imagegallery.widgets.SoloImage
import dagger.hilt.android.AndroidEntryPoint

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

                    val imgaes = remember {
                        mutableStateListOf<SinglePhotoX>()
                    }
//                    imgViewModel.imageData.observe(this, Observer {
//                        when (it) {
//                            is NetWorkResult.Loading -> {}
//                            is NetWorkResult.Error -> {
//                                Toast.makeText(this, "Error image", Toast.LENGTH_SHORT).show()
//                            }
//
//                            is NetWorkResult.Success -> {
//                                println("kanti response ${it.data?.photos?.photo.toString()}")
//                                it.data?.photos?.photo?.let { it1 -> imgaes.addAll(it1) }
//                            }
//                        }
//                    })

                    ImageList(imgViewModel)
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImageGalleryTheme {
        //  Greeting("Android")
    }
}