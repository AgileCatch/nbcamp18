package com.example.instagram

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.Data.UserinfoSingleton

class MyPage() :  AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        val backbutton = findViewById<ImageButton>(R.id.imb_back)
        val createbutton = findViewById<ImageButton>(R.id.imb_create)
        val dName = findViewById<TextView>(R.id.tv_name)
        val dProfileImgSquare = findViewById<ImageView>(R.id.imageButton)
        val dtoday = findViewById<TextView>(R.id.tv_today_num)
        val dintroduce = findViewById<TextView>(R.id.tv_description)
        val dIlchon = findViewById<TextView>(R.id.tv_1chon_num)
        val dFavorites = findViewById<TextView>(R.id.tv_favorites_num)
        val dMiniroom = findViewById<ImageView>(R.id.iv_miniroom)
        val droomname = findViewById<TextView>(R.id.roomname)

        val num = intent.getIntExtra("num", 0)
        val userinfoList = UserinfoSingleton.getUserinfoList()
        val userinfo = userinfoList[num]

        dName.text = userinfo.name
        dProfileImgSquare.setImageResource(userinfo.profileImg)
        dtoday.text = userinfo.today.toString()
        dintroduce.text = userinfo.description
        dIlchon.text = userinfo.ilchon.toString()
        dFavorites.text = userinfo.favorites.toString()
        dMiniroom.setImageResource(userinfo.miniroom)
        droomname.text = userinfo.roomname

        createbutton.setOnClickListener {
            val intent = Intent(this, EditMyPage::class.java)
            intent.putExtra("num", num)
            startActivity(intent)
        }

        backbutton.setOnClickListener {
            val intent = Intent(this, MainPage::class.java)
            startActivity(intent)
        }
    }
}