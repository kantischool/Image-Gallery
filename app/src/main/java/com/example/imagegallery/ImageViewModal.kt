package com.example.imagegallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagegallery.modals.FlickrResponse
import com.example.imagegallery.repo.ImageRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModal@Inject constructor(private val imageRepo: ImageRepo): ViewModel() {
    val imageData get() = imageRepo.imageData

    fun getImages(){
        viewModelScope.launch{
            imageRepo.loadImages()
        }
    }
}