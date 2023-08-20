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

class MyPage() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        // 뷰 요소 초기화
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

        // 이전 화면에서 전달받은 포지션 정보로 사용자 정보 가져오기
        val position = intent.getIntExtra("position", 0)
        Log.d("position", position.toString())
        val userinfoList = UserinfoSingleton.getUserinfoList()
        val userinfo = userinfoList[position]

        // 사용자 정보 화면에 표시
        dName.text = userinfo.name
        dtoday.text = userinfo.today.toString()
        dintroduce.text = userinfo.description
        dIlchon.text = userinfo.ilchon.toString()
        dFavorites.text = userinfo.favorites.toString()
        droomname.text = userinfo.roomname


        // 프로필 이미지 및 미니룸 이미지 표시
        if (userinfo.changedProfileImg == Uri.EMPTY) {
            dProfileImgSquare.setImageResource(userinfo.profileImg)
        } else {
            dProfileImgSquare.setImageURI(userinfo.changedProfileImg)
        }

        if (userinfo.changedMiniroomImg == Uri.EMPTY) {
            dMiniroom.setImageResource(userinfo.miniroom)
        } else {
            dMiniroom.setImageURI(userinfo.changedMiniroomImg)
        }

        // 댓글 리사이클러뷰 설정
        val rv_comment = findViewById<RecyclerView>(R.id.rv_comment)
        rv_comment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_comment.setHasFixedSize(true)
        rv_comment.adapter = CommentAdapter(userinfo.commentList)


        // 정보 수정 버튼 클릭 시 EditMyPage 화면으로 이동
        createbutton.setOnClickListener {
            val intent = Intent(this, EditMyPage::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        // 뒤로 가기 버튼 클릭 시 MainPage 화면으로 이동
        backbutton.setOnClickListener {
            val intent = Intent(this, MainPage::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        // 사진 버튼 클릭 시 PhotoAlbumPage 화면으로 이동
        photobutton.setOnClickListener {
            val intent = Intent(this, PhotoAlbumPage::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }
}