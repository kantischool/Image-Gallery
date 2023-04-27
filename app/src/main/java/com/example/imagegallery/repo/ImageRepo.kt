package com.example.imagegallery.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.imagegallery.ApiInterface.ImageApi
import com.example.imagegallery.NetWorkResult
import com.example.imagegallery.modals.FlickrResponse
import javax.inject.Inject

class ImageRepo @Inject constructor(private val imageApi: ImageApi) {

    private val _imageData: MutableLiveData<NetWorkResult<FlickrResponse>> = MutableLiveData()
    val imageData: LiveData<NetWorkResult<FlickrResponse>> get() = _imageData


    suspend fun loadImages(){
        _imageData.postValue(NetWorkResult.Loading())
        val response = imageApi.fetchImage()

        if (response.isSuccessful && response.body() != null){
            _imageData.postValue(NetWorkResult.Success(response.body()!!))
        }
        else if(response.errorBody() != null){
            _imageData.postValue(NetWorkResult.Error("Some thing went Wrong"))
        }
        else{
            _imageData.postValue(NetWorkResult.Error("Some thing went Wrong"))
        }
    }
}