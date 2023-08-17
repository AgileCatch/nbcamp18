package com.example.instagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.FeedAdapter
import com.example.instagram.Data.Userinfo


class MainPage : AppCompatActivity() {

    var userList = listOf<Userinfo>(
        Userinfo("김영현", "yeonghyeon", R.drawable.girl1,0, "안녕하세요\n김영현 미니홈피입니다.", 100, 900, R.drawable.miniroom),
        Userinfo("추지연", "jiyeon", R.drawable.girl2,0, "안녕하세요\n추지연 미니홈피입니다.", 10, 900, R.drawable.miniroom2),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page_activity)



        val ibProfile1 = findViewById<ImageButton>(R.id.ib_profile1)
        val ibProfile2 = findViewById<ImageButton>(R.id.ib_profile2)
        val ibProfile3 = findViewById<ImageButton>(R.id.ib_profile3)
        val ibProfile4 = findViewById<ImageButton>(R.id.ib_profile4)


        //회원가입에서 데이터 전달 받아서 profileList에 추가한다
        //profileList.add(Userinfo(전달받은 데이터))


        moveDetailPage(ibProfile1, 0)
        moveDetailPage(ibProfile3, 1)


        val feedList = arrayListOf(
            Userinfo(userList[0].id, userList[0].profileImg, userList[0].miniroom, userList[0].description),
            Userinfo(userList[1].id, userList[1].profileImg, userList[1].miniroom, userList[1].description),
            Userinfo(userList[0].id, userList[0].profileImg, userList[0].miniroom, userList[0].description),
            Userinfo(userList[1].id, userList[1].profileImg, userList[1].miniroom, userList[1].description),
        )

        val rv_feed = findViewById<RecyclerView>(R.id.rv_feed)
        rv_feed.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_feed.setHasFixedSize(true)

        rv_feed.adapter = FeedAdapter(feedList, userList)

    }

    fun moveDetailPage(Imb:ImageButton, i:Int){

        Imb.setOnClickListener {
            val intent = Intent(this, DetailPage::class.java)
            var userinfo = userList[i]
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

    }
}