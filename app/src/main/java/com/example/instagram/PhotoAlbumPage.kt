package com.example.instagram

import DataManager
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.PhotoAlbumAdapter
import com.example.instagram.Data.PhotoCard

private val photocardList = arrayListOf<PhotoCard>()

class PhotoAlbumPage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_album_page)


        //뒤로가기 버튼작동
        val backButton = findViewById<ImageButton>(R.id.imb_back)
        backButton.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        // 사진첩 리사이클 뷰 연결
        val rv_album = findViewById<RecyclerView>(R.id.rv_album)
        rv_album.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_album.setHasFixedSize(true)

        // DataManager에서 사진 데이터를 가져와서 어댑터에 설정
        val photoCardList = DataManager.getPhotoCardList() as ArrayList<PhotoCard>
        rv_album.adapter = PhotoAlbumAdapter(photoCardList)

    }

}