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
class ImageViewModal@Inject constructor(private val imageRepo: ImageRepoImpl, val imgRepo: ImageRepo): ViewModel() {
    val imageData get() = imgRepo.imageData

    fun searchImage(catName: String){
        viewModelScope.launch{
            imgRepo.searchImages(catName)
        }
    }

    val imagePager = Pager(PagingConfig(pageSize = 5)) {
        ImageDataSource(imageRepo, null)
    }.flow.cachedIn(viewModelScope)

//    val imageSearchPager = Pager(PagingConfig(pageSize = 5)) {
//        ImageDataSource(imageRepo, catName)
//    }.flow.cachedIn(viewModelScope)
}