package com.example.instagram

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.CommentAdapter
import com.example.instagram.Adapter.EditCommentAdapter
import com.example.instagram.Data.UserinfoSingleton

private var profileUri : Uri = Uri.EMPTY
private var miniroomUri : Uri = Uri.EMPTY

class EditMyPage :  AppCompatActivity(){

    private lateinit var profileactivityResult: ActivityResultLauncher<Intent>
    private lateinit var miniroomactivityResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_mypage)

        val backbutton = findViewById<ImageButton>(R.id.imb_back)
        val savebutton = findViewById<ImageButton>(R.id.imb_save)
        val profileSelectButton = findViewById<ImageButton>(R.id.imb_profile)
        val miniroomSelectButton = findViewById<ImageButton>(R.id.imb_miniroom)
        val dName = findViewById<TextView>(R.id.tv_name)
        val dToday = findViewById<TextView>(R.id.tv_today_num)
        val dDescription = findViewById<EditText>(R.id.et_description)
        val dIlchon = findViewById<TextView>(R.id.tv_1chon_num)
        val dFavorites = findViewById<TextView>(R.id.tv_favorites_num)
        val dRoomname = findViewById<EditText>(R.id.et_roomname)

        val photobutton = findViewById<Button>(R.id.bt_photo)

        val position = intent.getIntExtra("position", 0)
        val userinfoList = UserinfoSingleton.getUserinfoList()
        val userinfo = userinfoList[position]
        Log.d("userinfo", userinfo.toString())
        Log.d("position", position.toString())

        profileactivityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val data: Intent? = result.data
                profileUri = data?.data as Uri

                profileSelectButton.setImageURI(profileUri)
            }
        }

        miniroomactivityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val data: Intent? = result.data
                miniroomUri = data?.data as Uri

                miniroomSelectButton.setImageURI(miniroomUri)
            }
        }

        profileSelectButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            profileactivityResult.launch(intent)
        }

        miniroomSelectButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            miniroomactivityResult.launch(intent)
        }

        dName.text = userinfo.name
        dToday.text = userinfo.today.toString()
        dDescription.setText(userinfo.description)
        dIlchon.text = userinfo.ilchon.toString()
        dFavorites.text = userinfo.favorites.toString()
        dRoomname.setText(userinfo.roomname)

        if(userinfo.changedProfileImg == Uri.EMPTY){
            profileSelectButton.setImageResource(userinfo.profileImg)
        }else{
            profileSelectButton.setImageURI(userinfo.changedProfileImg)
        }

        if(userinfo.changedMiniroomImg == Uri.EMPTY){
            miniroomSelectButton.setImageResource(userinfo.miniroom)
        }else{
            miniroomSelectButton.setImageURI(userinfo.changedMiniroomImg)
        }

        val rv_comment = findViewById<RecyclerView>(R.id.rv_comment)
        rv_comment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_comment.setHasFixedSize(true)

        rv_comment.adapter = EditCommentAdapter(userinfo.commentList)

        savebutton.setOnClickListener {
            val intent = Intent(this, MyPage::class.java)

            UserinfoSingleton.changeUserImg(
                userinfo,
                profileUri,
                miniroomUri
            )

            intent.putExtra("position", position)
            startActivity(intent)
        }

        backbutton.setOnClickListener {
            val intent = Intent(this, MyPage::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
            finish()
        }

        photobutton.setOnClickListener {
            val intent = Intent(this, EditPhotoPage::class.java)
            startActivity(intent)
        }

    }



}