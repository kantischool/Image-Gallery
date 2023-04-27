package com.example.imagegallery.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.imagegallery.ApiInterface.ImageApi
import com.example.imagegallery.ImageRepoImpl
import com.example.imagegallery.NetWorkResult
import com.example.imagegallery.modals.FlickrResponse
import retrofit2.Response
import javax.inject.Inject

class ImageRepo (private val imageApi: ImageApi) : ImageRepoImpl {

    private val _imageData: MutableLiveData<NetWorkResult<FlickrResponse>> = MutableLiveData()
    val imageData: LiveData<NetWorkResult<FlickrResponse>> get() = _imageData


//    suspend fun loadImages(page: Int) : FlickrResponse
//    override suspend fun getImage(page: Int): Response<FlickrResponse> {
//        return imageApi.fetchImage(page)
//    }

    override suspend fun loadImages(page: Int): Response<FlickrResponse> {
        return imageApi.fetchImage(page)
    }

//    {
//        _imageData.postValue(NetWorkResult.Loading())
//        val response = imageApi.fetchImage(page)
//
//        if (response.isSuccessful && response.body() != null){
//            _imageData.postValue(NetWorkResult.Success(response.body()!!))
//        }
//        else if(response.errorBody() != null){
//            _imageData.postValue(NetWorkResult.Error("Some thing went Wrong"))
//        }
//        else{
//            _imageData.postValue(NetWorkResult.Error("Some thing went Wrong"))
//        }
//    }
}