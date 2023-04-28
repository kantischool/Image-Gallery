package com.example.imagegallery

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imagegallery.modals.SinglePhotoX

class ImageDataSource(private val repo: ImageRepoImpl, val catName: String?) : PagingSource<Int, SinglePhotoX>() {
    override fun getRefreshKey(state: PagingState<Int, SinglePhotoX>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SinglePhotoX> {

        if (catName == null)
            return try {
                val nextPageNumber = params.key ?: 1
                val response = repo.loadImages(nextPageNumber)
                LoadResult.Page(
                    data = response.body()?.photos!!.photo,
                    prevKey = null,
                    nextKey = if (response.body()!!.photos.photo.isNotEmpty()) response.body()!!.photos.page + 1 else null
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        else
            return try {
                val nextPageNumber = params.key ?: 1
                val response = repo.loadSearchImages(nextPageNumber, catName)
                LoadResult.Page(
                    data = response.body()?.photos!!.photo,
                    prevKey = null,
                    nextKey = if (response.body()!!.photos.photo.isNotEmpty()) response.body()!!.photos.page + 1 else null
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }

    }
}