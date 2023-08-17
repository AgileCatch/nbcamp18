package com.example.instagram

import android.content.Intent
import android.net.Uri
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


        val userinfoList = UserinfoSingleton.getUserinfoList()
        imageButtonChange("김영현", ibProfile1)
        imageButtonChange("추지연", ibProfile3)


        ibProfile1.setOnClickListener {
            val intent = Intent(this, MyPage::class.java)
            val userinfo = userinfoList[0]

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
                userinfo.roomname,
                userinfo.changedProfileImg,
                userinfo.changedMiniroomImg
            )

            intent.putExtra("num", 0)
            startActivity(intent)
        }


        ibProfile3.setOnClickListener {
            val intent = Intent(this, DetailPage::class.java)
            val userinfo = userinfoList[1]

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
                userinfo.roomname,
                userinfo.changedProfileImg,
                userinfo.changedMiniroomImg
            )

            intent.putExtra("position", 1)
            startActivity(intent)
        }

        val feedList = arrayListOf(
            //수정 필요
            userinfoList.find{it.name == "김영현"} as Userinfo,
            userinfoList.find{it.name == "추지연"} as Userinfo,
        )

        val rv_feed = findViewById<RecyclerView>(R.id.rv_feed)
        rv_feed.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_feed.setHasFixedSize(true)

        rv_feed.adapter = FeedAdapter(feedList, userinfoList)

    }

    fun imageButtonChange(username:String, ImgButton:ImageButton){
        var userinfo = userinfoList.find { it.name == username } as Userinfo

        if(userinfo.changedProfileImg == Uri.EMPTY){
            ImgButton.setImageResource(userinfo.profileImg)
        }else{
            ImgButton.setImageURI(userinfo.changedProfileImg)
        }
    }

    fun moveDetailPage(Imb:ImageButton, i:Int){

        Imb.setOnClickListener {
            val intent = Intent(this, DetailPage::class.java)
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
                userinfo.roomname,
                userinfo.changedProfileImg,
                userinfo.changedMiniroomImg
            )

            intent.putExtra("num", i)
            startActivity(intent)

        }

    }
}