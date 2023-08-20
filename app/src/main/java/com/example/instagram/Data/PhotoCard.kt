package com.example.instagram.Data

import android.net.Uri

data class PhotoCard (
    var title:String,
    val imageUri: Uri?,
    val datetime:String,
    var content:String
)
