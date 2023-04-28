package com.example.imagegallery.ApiInterface

import com.example.imagegallery.modals.FlickrResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImageApi {

    @GET("?method=flickr.photos.getRecent&per_page=40&page=1&api_key=9150f9f8653b0f6c1e81928665476c3a&format=json&nojsoncallback=1&extras=url_s")
    suspend fun fetchImage(@Query("page") pageNo: Int) : Response<FlickrResponse>

    @GET("?method=flickr.photos.search&api_key=9150f9f8653b0f6c1e81928665476c3a&format=json&nojsoncallback=1&extras=url_s&text=cat")
    suspend fun fetchSearchImage(@Query("text") text: String) : Response<FlickrResponse>
}