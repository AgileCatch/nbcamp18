package com.example.instagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.FeedAdapter
import com.example.instagram.Data.Feed
import com.example.instagram.Data.Userinfo

class MainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page_activity)

        var proflieList = listOf<Userinfo>(
            Userinfo("김영현", "yeonghyeon", R.drawable.girl1,0, "안녕하세요\n김영현 미니홈피입니다.", 100, 900, R.drawable.miniroom),
            Userinfo("추지연", "jiyeon", R.drawable.man1,0, "안녕하세요\n추지연 미니홈피입니다.", 10, 900, R.drawable.miniroom2),
        )

        val ibProfile1 = findViewById<ImageButton>(R.id.ib_profile1)
        val ibProfile2 = findViewById<ImageButton>(R.id.ib_profile2)


        ibProfile1.setOnClickListener {
            val intent = Intent(this, DetailPage::class.java)
            var userinfo = proflieList[0]
            userinfo.today = userinfo.today + 1
            intent.putExtra("name", userinfo.name)
            intent.putExtra("profileImg", userinfo.profileImg)
            intent.putExtra("today", userinfo.today)
            intent.putExtra("description", userinfo.description)
            intent.putExtra("ilchon", userinfo.ilchon)
            intent.putExtra("favorites", userinfo.favorites)
            intent.putExtra("miniroomImg", userinfo.miniroom)
            startActivity(intent)
        }

        ibProfile2.setOnClickListener {
            val intent = Intent(this, DetailPage::class.java)
            var userinfo = proflieList[1]
            userinfo.today = userinfo.today + 1
            intent.putExtra("name", userinfo.name)
            intent.putExtra("profileImg", userinfo.profileImg)
            intent.putExtra("today", userinfo.today)
            intent.putExtra("description", userinfo.description)
            intent.putExtra("ilchon", userinfo.ilchon)
            intent.putExtra("favorites", userinfo.favorites)
            intent.putExtra("miniroomImg", userinfo.miniroom)
            startActivity(intent)
        }


        val feedList = arrayListOf(
            Feed(proflieList[0].profileImg, proflieList[0].nickname, proflieList[0].miniroom, proflieList[0].description),
            Feed(proflieList[1].profileImg, proflieList[1].nickname, proflieList[1].miniroom, proflieList[1].description),
            Feed(R.drawable.girl2, "seunghyeon", R.drawable.miniroom3, "미니룸입니다!"),
            Feed(R.drawable.man2, "joohwan", R.drawable.miniroom4, "미니룸입니다!"),

        )

        val rv_feed = findViewById<RecyclerView>(R.id.rv_feed)
        rv_feed.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_feed.setHasFixedSize(true)

        rv_feed.adapter = FeedAdapter(feedList)

    }
}