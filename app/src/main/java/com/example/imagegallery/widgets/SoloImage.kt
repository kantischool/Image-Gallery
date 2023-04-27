package com.example.imagegallery.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.imagegallery.R
import com.example.imagegallery.modals.SinglePhotoX
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SoloImage(data: SinglePhotoX){
    GlideImage(requestOptions = {
        RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
    },
        imageModel = { data.url_s },
        modifier = Modifier
            .size(100.dp).padding(6.dp),

        success = { imageState ->
            imageState.imageBitmap?.let {
                Image(
                    bitmap = it,
                    modifier = Modifier.size(100.dp),
                    contentDescription = "Nice Image",

                    )
            }
        },
        failure = {
            Image(
                painter = painterResource(R.drawable.error_image),
                contentDescription = "Image Not Loaded",
                modifier = Modifier.size(100.dp)
            )
        },
        loading = {
            Image(
                painter = painterResource(R.drawable.error_image),
                contentDescription = "Image Loading",
                modifier = Modifier.size(100.dp)
            )
        })
}