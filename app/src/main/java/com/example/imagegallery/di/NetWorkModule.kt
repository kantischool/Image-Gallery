package com.example.imagegallery.di

import com.example.imagegallery.ApiInterface.ImageApi
import com.example.imagegallery.Constant
import com.example.imagegallery.ImageRepoImpl
import com.example.imagegallery.repo.ImageRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetWorkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideImageApi(retrofit: Retrofit): ImageApi{
        return retrofit.create(ImageApi::class.java)
    }

    @Provides
    fun provideUserRepository(api: ImageApi): ImageRepoImpl = ImageRepo(api)
}