package com.example.imagegallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.imagegallery.modals.FlickrResponse
import com.example.imagegallery.repo.ImageRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModal@Inject constructor(private val imageRepo: ImageRepoImpl): ViewModel() {
  //  val imageData get() = imageRepo.imageData

//    fun getImages(){
//        viewModelScope.launch{
//            imageRepo.loadImages()
//        }
//    }

    val imagePager = Pager(PagingConfig(pageSize = 5)) {
        ImageDataSource(imageRepo)
    }.flow.cachedIn(viewModelScope)
}