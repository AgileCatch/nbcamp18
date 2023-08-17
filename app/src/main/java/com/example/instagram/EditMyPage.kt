package com.example.instagram

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.Data.UserinfoSingleton

class EditMyPage :  AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_mypage)

        val backbutton = findViewById<ImageButton>(R.id.imb_back)
        val savebutton = findViewById<ImageButton>(R.id.imb_save)
        val profileSelectButton = findViewById<ImageButton>(R.id.imb_profile)
        val miniroomSelectButton = findViewById<ImageButton>(R.id.imb_miniroom)
        val dName = findViewById<TextView>(R.id.tv_name)
        val dtoday = findViewById<TextView>(R.id.tv_today_num)
        val ddescription = findViewById<EditText>(R.id.et_description)
        val dIlchon = findViewById<TextView>(R.id.tv_1chon_num)
        val dFavorites = findViewById<TextView>(R.id.tv_favorites_num)
        val droomname = findViewById<EditText>(R.id.et_roomname)

        val num = intent.getIntExtra("num", 0)
        val userinfoList = UserinfoSingleton.getUserinfoList()
        val userinfo = userinfoList[num]

        profileSelectButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
        }

        dName.text = userinfo.name
        dtoday.text = userinfo.today.toString()
        ddescription.setText(userinfo.description)
        dIlchon.text = userinfo.ilchon.toString()
        dFavorites.text = userinfo.favorites.toString()
        droomname.setText(userinfo.roomname)

        savebutton.setOnClickListener {
            val intent = Intent(this, MyPage::class.java)

            UserinfoSingleton.updateUserinfo(
                userinfo,
                userinfo.name,
                userinfo.id,
                userinfo.profileImg,
                userinfo.today,
                ddescription.text.toString(),
                userinfo.ilchon,
                userinfo.favorites,
                userinfo.miniroom,
                droomname.text.toString()
            )

            intent.putExtra("num", num)
            startActivity(intent)
        }

        backbutton.setOnClickListener {
            val intent = Intent(this, MyPage::class.java)
            intent.putExtra("num", num)
            startActivity(intent)
        }

    }

}