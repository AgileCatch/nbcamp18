package com.example.instagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.FeedAdapter
import com.example.instagram.Data.Feed

class MainPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page_activity)

        val profileList = arrayListOf(
            Feed(R.drawable.girl1, "yeonghyeon", R.drawable.miniroom, "미니룸입니다!"),
            Feed(R.drawable.man1, "jiyeon", R.drawable.miniroom2, "미니룸입니다!"),
            Feed(R.drawable.girl2, "seunghyeon", R.drawable.miniroom3, "미니룸입니다!"),
            Feed(R.drawable.man2, "joohwan", R.drawable.miniroom4, "미니룸입니다!"),

        )

        val rv_feed = findViewById<RecyclerView>(R.id.rv_feed)
        rv_feed.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_feed.setHasFixedSize(true)

        rv_feed.adapter = FeedAdapter(profileList)

    }
}