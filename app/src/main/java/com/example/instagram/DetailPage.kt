package com.example.instagram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.CommentAdapter
import com.example.instagram.Adapter.FeedAdapter
import com.example.instagram.Data.Comment

class DetailPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage)

        val commentList = arrayListOf(
            Comment("김영현", "관심일촌", R.drawable.girl1, "2023.08.16","18조 화이팅!"),
            Comment("추지연", "어느별에서왔니도대체", R.drawable.girl2, "2023.08.15","■■■■■□90% 충전중"),
            Comment("이승현", "우린 먼가 달라", R.drawable.man1, "2023.08.14","거기 하늘라이프죠?"),
            Comment("안주환", "너는나의엔돌핀", R.drawable.man2, "2023.08.13","Very important person"),

            )

        val rv_comment = findViewById<RecyclerView>(R.id.rv_comment)
        rv_comment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_comment.setHasFixedSize(true)

        rv_comment.adapter = CommentAdapter(commentList)
    }
}