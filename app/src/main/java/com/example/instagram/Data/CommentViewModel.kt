package com.example.instagram.Data

import androidx.lifecycle.ViewModel

class CommentViewModel : ViewModel() {
    val commentList = mutableListOf<Comment>()
}