package com.example.instagram

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.CommentAdapter
import com.example.instagram.Data.Comment

val commentList = arrayListOf(
    Comment("김영현", "관심일촌", R.drawable.girl1, "2023.08.16","18조 화이팅!"),
    Comment("추지연", "어느별에서왔니도대체", R.drawable.girl2, "2023.08.15","■■■■■□90% 충전중"),
    Comment("이승현", "우린 먼가 달라", R.drawable.man1, "2023.08.14","거기 하늘라이프죠?"),
    Comment("안주환", "너는나의엔돌핀", R.drawable.man2, "2023.08.13","Very important person"),

    )

class DetailPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage)

        val toolbarName = findViewById<TextView>(R.id.rightText)
        val dName = findViewById<TextView>(R.id.tv_name)
        val dProfileImg = findViewById<ImageView>(R.id.rightImageView)
        val dProfileImgSquare = findViewById<ImageButton>(R.id.imb_profile)
        val dtoday = findViewById<TextView>(R.id.tv_today_num)
        val dintroduce = findViewById<TextView>(R.id.tv_description)
        val dIlchon = findViewById<TextView>(R.id.tv_1chon_num)
        val dFavorites = findViewById<TextView>(R.id.tv_favorites_num)
        val dMiniroom = findViewById<ImageView>(R.id.iv_miniroom)

        val commentButton = findViewById<Button>(R.id.button)
        val Etilchon = findViewById<EditText>(R.id.et_ilchon)
        val Etcontent  = findViewById<EditText>(R.id.et_content)

        val backButton = findViewById<ImageButton>(R.id.imb_back)
        backButton.setOnClickListener {
            finish()
        }



        val rv_comment = findViewById<RecyclerView>(R.id.rv_comment)
        rv_comment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_comment.setHasFixedSize(true)

        rv_comment.adapter = CommentAdapter(commentList)

        commentButton.setOnClickListener{
            var ilchonNickname = Etilchon.text.toString()
            var ilchonText = Etcontent.text.toString()

            commentList.add(Comment("이름", ilchonNickname, R.drawable.man1, "2023.08.17", ilchonText))
            Log.d("commentList", commentList.toString())
            (rv_comment.adapter as? CommentAdapter)?.updateData(commentList)

        }


        val name = intent.getStringExtra("name")
        val profileImg = intent.getIntExtra("profileImg", 0)
        var today = intent.getIntExtra("today", 0)
        var description = intent.getStringExtra("description")
        var ilchon = intent.getIntExtra("ilchon", 0)
        var favorites = intent.getIntExtra("favorites", 0)
        var miniroom = intent.getIntExtra("miniroomImg", 0)

        toolbarName.text = "${name}의 미니홈피"
        dName.text = name
        dProfileImg.setImageResource(profileImg)
        dProfileImgSquare.setImageResource(profileImg)
        dtoday.text = today.toString()
        dintroduce.text = description
        dIlchon.text = ilchon.toString()
        dFavorites.text = favorites.toString()
        dMiniroom.setImageResource(miniroom)

    }

}