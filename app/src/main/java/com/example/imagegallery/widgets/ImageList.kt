package com.example.imagegallery.widgets


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.PointerIconDefaults.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.imagegallery.ImageViewModal
import com.example.imagegallery.R
import com.example.imagegallery.modals.SinglePhotoX

@Composable
fun ImageList(viewModel: ImageViewModal){
    val imgData: LazyPagingItems<SinglePhotoX> = viewModel.imagePager.collectAsLazyPagingItems()

//    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 100.dp)){
//        items(imgData){
//
//        }
//    }
    
    
    LazyColumn{
        items(imgData){ item ->
            item?.let { SoloImage(data = it) }

            when (imgData.loadState.append) {
                is LoadState.NotLoading -> Unit
                LoadState.Loading -> {
                    LoadingItem()
                }
                is LoadState.Error -> {
                    ErrorItem(message = (imgData.loadState.append as LoadState.Error).error.message.toString())
                }
            }

            when (imgData.loadState.refresh) {
                is LoadState.NotLoading -> Unit
                LoadState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is LoadState.Error -> TODO()
            }
        }
    }
}


@Composable
fun ErrorItem(message: String) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(8.dp)
        ) {
            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .width(42.dp)
                    .height(42.dp),
                painter = painterResource(id = R.drawable.error_image),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White)
            )
            Text(
                color = Color.White,
                text = message,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(CenterVertically)
            )
        }
    }
}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(42.dp)
                .height(42.dp)
                .padding(8.dp),
            strokeWidth = 5.dp
        )

    }
}