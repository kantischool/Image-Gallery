package com.example.imagegallery.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.imagegallery.ApiInterface.ImageApi
import com.example.imagegallery.ImageRepoImpl
import com.example.imagegallery.NetWorkResult
import com.example.imagegallery.modals.FlickrResponse
import retrofit2.Response
import javax.inject.Inject

class ImageRepo @Inject constructor (private val imageApi: ImageApi) : ImageRepoImpl {

    private val _imageData: MutableLiveData<NetWorkResult<FlickrResponse>> = MutableLiveData()
    val imageData: LiveData<NetWorkResult<FlickrResponse>> get() = _imageData


//    suspend fun loadImages(page: Int) : FlickrResponse
//    override suspend fun getImage(page: Int): Response<FlickrResponse> {
//        return imageApi.fetchImage(page)
//    }

    override suspend fun loadImages(page: Int): Response<FlickrResponse> {
        return imageApi.fetchImage(page)
    }

//    override suspend fun loadSearchImages(page: Int, catName: String): Response<FlickrResponse> {
//        return imageApi.fetchSearchImage(catName)
//    }

    suspend fun searchImages(cat: String) {
        _imageData.postValue(NetWorkResult.Loading())
        val response = imageApi.fetchSearchImage(cat)

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