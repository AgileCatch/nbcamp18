package com.example.instagram

import android.media.Image
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class DetailPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailpage_activity)

        val toolbarName = findViewById<TextView>(R.id.rightText)
        val dName = findViewById<TextView>(R.id.tv_name)
        val dProfileImg = findViewById<ImageView>(R.id.rightImageView)
        val dtoday = findViewById<TextView>(R.id.tv_today_num)
        val dintroduce = findViewById<TextView>(R.id.tv_description)
        val dIlchon = findViewById<TextView>(R.id.tv_1chon_num)
        val dFavorites = findViewById<TextView>(R.id.tv_favorites_num)
        val dMiniroom = findViewById<ImageView>(R.id.iv_miniroom)

        val backButton = findViewById<ImageButton>(R.id.imb_back)
        backButton.setOnClickListener {
            finish()
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
        dtoday.text = today.toString()
        dintroduce.text = description
        dIlchon.text = ilchon.toString()
        dFavorites.text = favorites.toString()
        dMiniroom.setImageResource(miniroom)

    }

}