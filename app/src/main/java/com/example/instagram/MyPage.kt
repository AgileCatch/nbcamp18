package com.example.instagram

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.CommentAdapter
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
        val photobutton = findViewById<Button>(R.id.bt_photo)

        val position = intent.getIntExtra("position", 0)
        Log.d("position", position.toString())
        val userinfoList = UserinfoSingleton.getUserinfoList()
        val userinfo = userinfoList[position]

        dName.text = userinfo.name

        dtoday.text = userinfo.today.toString()
        dintroduce.text = userinfo.description
        dIlchon.text = userinfo.ilchon.toString()
        dFavorites.text = userinfo.favorites.toString()
        droomname.text = userinfo.roomname

        if(userinfo.changedProfileImg == Uri.EMPTY){
            dProfileImgSquare.setImageResource(userinfo.profileImg)
        }else{
            dProfileImgSquare.setImageURI(userinfo.changedProfileImg)
        }

        if(userinfo.changedMiniroomImg == Uri.EMPTY){
            dMiniroom.setImageResource(userinfo.miniroom)
        }else{
            dMiniroom.setImageURI(userinfo.changedMiniroomImg)
        }

        val rv_comment = findViewById<RecyclerView>(R.id.rv_comment)
        rv_comment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_comment.setHasFixedSize(true)

        rv_comment.adapter = CommentAdapter(userinfo.commentList)



        createbutton.setOnClickListener {
            val intent = Intent(this, EditMyPage::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        backbutton.setOnClickListener {
            val intent = Intent(this, MainPage::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        photobutton.setOnClickListener {
            val intent = Intent(this, PhotoAlbumPage::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }
}