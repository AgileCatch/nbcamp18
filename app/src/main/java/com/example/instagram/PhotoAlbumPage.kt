package com.example.instagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.PhotoAlbumAdapter

class PhotoAlbumPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_album_page)

        //뒤로가기 버튼작동
        val backButton = findViewById<ImageButton>(R.id.imb_back)
        backButton.setOnClickListener {
            finish()
        }

        //사진첩 리사이클 뷰 연결
        val rv_album = findViewById<RecyclerView>(R.id.rv_album)
        rv_album.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_album.setHasFixedSize(true)

        rv_album.adapter = PhotoAlbumAdapter(photocardList)
    }
}