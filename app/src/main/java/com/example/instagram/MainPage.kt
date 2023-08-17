package com.example.instagram

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.FeedAdapter
import com.example.instagram.Data.Userinfo
import com.example.instagram.Data.UserinfoSingleton

class MainPage : AppCompatActivity() {

    val userinfoList = UserinfoSingleton.getUserinfoList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page_activity)

        UserinfoSingleton.addUserinfo(Userinfo("김영현", "yeonghyeon", R.drawable.girl1,0, "안녕하세요\n김영현 미니홈피입니다.", 100, 900, R.drawable.miniroom, "영현님의 미니룸"))
        UserinfoSingleton.addUserinfo(Userinfo("추지연", "jiyeon", R.drawable.girl2,0, "안녕하세요\n추지연 미니홈피입니다.", 10, 900, R.drawable.miniroom2, "지연님의 미니룸"))

        val ibProfile1 = findViewById<ImageButton>(R.id.ib_profile1)
        val ibProfile2 = findViewById<ImageButton>(R.id.ib_profile2)
        val ibProfile3 = findViewById<ImageButton>(R.id.ib_profile3)
        val ibProfile4 = findViewById<ImageButton>(R.id.ib_profile4)


        //회원가입에서 데이터 전달 받아서 profileList에 추가한다
        //profileList.add(Userinfo(전달받은 데이터))


        moveDetailPage(ibProfile1, 0)
        moveDetailPage(ibProfile3, 1)


        val feedList = arrayListOf(
            userinfoList[0],
            userinfoList[1]
        )

        val rv_feed = findViewById<RecyclerView>(R.id.rv_feed)
        rv_feed.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_feed.setHasFixedSize(true)

        rv_feed.adapter = FeedAdapter(feedList, userinfoList)

    }

    fun moveDetailPage(Imb:ImageButton, i:Int){

        Imb.setOnClickListener {
            val intent = Intent(this, MyPage::class.java)
            val userinfo = userinfoList[i]

            UserinfoSingleton.updateUserinfo(
                userinfo,
                userinfo.name,
                userinfo.id,
                userinfo.profileImg,
                userinfo.today + 1,
                userinfo.description,
                userinfo.ilchon,
                userinfo.favorites,
                userinfo.miniroom,
                userinfo.roomname
            )

            intent.putExtra("num", i)
            startActivity(intent)

        }

    }
}