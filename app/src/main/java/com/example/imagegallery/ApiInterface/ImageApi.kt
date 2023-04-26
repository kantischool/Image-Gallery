package com.example.imagegallery.ApiInterface

import com.example.imagegallery.modals.FlickrResponse
import retrofit2.Response
import retrofit2.http.GET

interface ImageApi {

    @GET("?method=flickr.photos.getRecent&per_page=20&page=1&api_key=9150f9f8653b0f6c1e81928665476c3a&format=json&nojsoncallback=1&extras=url_s")
    suspend fun fetchImage() : Response<FlickrResponse>
}