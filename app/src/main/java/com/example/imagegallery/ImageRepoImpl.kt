package com.example.imagegallery

import com.example.imagegallery.modals.FlickrResponse
import retrofit2.Response

interface ImageRepoImpl {
    suspend fun loadImages(page: Int): Response<FlickrResponse>
    suspend fun loadSearchImages(page: Int, catName:String): Response<FlickrResponse>
}