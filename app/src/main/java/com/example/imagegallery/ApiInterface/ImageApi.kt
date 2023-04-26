package com.example.imagegallery.ApiInterface

import com.example.imagegallery.modals.FlickrResponse
import retrofit2.Response
import retrofit2.http.GET

interface ImageApi {

    @GET()
    fun fetchImage() : Response<FlickrResponse>
}