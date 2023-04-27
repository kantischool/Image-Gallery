package com.example.imagegallery

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imagegallery.di.NetWorkModule
import com.example.imagegallery.repo.ImageRepo
import com.example.imagegallery.ui.theme.ImageGalleryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val imgViewModel by viewModels<ImageViewModal>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imgViewModel.getImages()
        setContent {
            ImageGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    imgViewModel.imageData.observe(this, Observer {
                        when(it){
                            is NetWorkResult.Loading -> {}
                            is NetWorkResult.Error -> {
                                Toast.makeText(this, "Error image", Toast.LENGTH_SHORT).show()
                            }
                            is NetWorkResult.Success -> {
                                println( "kanti response ${it.data?.photos?.photo.toString()}")
                            }
                        }
                    })
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImageGalleryTheme {
        Greeting("Android")
    }
}