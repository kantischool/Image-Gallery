package com.example.imagegallery.di

import com.example.imagegallery.ApiInterface.ImageApi
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
            .baseUrl("")
            .build()
    }

    @Singleton
    @Provides
    fun provideImageApi(retrofit: Retrofit): ImageApi{
        return retrofit.create(ImageApi::class.java)
    }
}