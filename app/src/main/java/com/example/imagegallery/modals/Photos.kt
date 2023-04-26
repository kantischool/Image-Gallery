package com.example.imagegallery.modals

data class Photos (
    val page : Int,
    val pages: Int,
    val perpage: Int,
    val total : Long,
    val photo: ArrayList<SinglePhotoX>
        )