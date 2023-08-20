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
import com.example.instagram.Adapter.EditCommentAdapter
import com.example.instagram.Data.UserinfoSingleton

private var profileUri: Uri = Uri.EMPTY // 프로필 이미지의 Uri를 저장하는 변수
private var miniroomUri: Uri = Uri.EMPTY // 미니룸 이미지의 Uri를 저장하는 변수

class EditMyPage : AppCompatActivity() {

    private lateinit var profileactivityResult: ActivityResultLauncher<Intent>
    private lateinit var miniroomactivityResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_mypage)

        // 각종 뷰 및 UI 요소 초기화
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
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val data: Intent? = result.data
                profileUri = data?.data as Uri

                profileSelectButton.setImageURI(profileUri)
            }
        }

        miniroomactivityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val data: Intent? = result.data
                miniroomUri = data?.data as Uri

                miniroomSelectButton.setImageURI(miniroomUri)
            }
        }

        // 프로필 이미지 선택 버튼에 대한 동작 설정
        profileSelectButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            profileactivityResult.launch(intent)
        }

        // 미니룸 이미지 선택 버튼에 대한 동작 설정
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

        if (userinfo.changedProfileImg == Uri.EMPTY) {
            profileSelectButton.setImageResource(userinfo.profileImg)
        } else {
            profileSelectButton.setImageURI(userinfo.changedProfileImg)
        }

        if (userinfo.changedMiniroomImg == Uri.EMPTY) {
            miniroomSelectButton.setImageResource(userinfo.miniroom)
        } else {
            miniroomSelectButton.setImageURI(userinfo.changedMiniroomImg)
        }

        val rv_comment = findViewById<RecyclerView>(R.id.rv_comment)
        rv_comment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_comment.setHasFixedSize(true)

        rv_comment.adapter = EditCommentAdapter(userinfo.commentList)

        // 저장 버튼 클릭 시 사용자 정보를 변경하고 MyPage 화면으로 이동
        savebutton.setOnClickListener {
            val intent = Intent(this, MyPage::class.java)

            UserinfoSingleton.changeUserEdit(
                userinfo,
                dDescription.text.toString(),
                profileUri,
                miniroomUri
            )

            intent.putExtra("position", position)
            startActivity(intent)

        }

        // 뒤로 가기 버튼 클릭 시 MyPage 화면으로 이동
        backbutton.setOnClickListener {
            val intent = Intent(this, MyPage::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        // 사진 버튼 클릭 시 EditPhotoPage 화면으로 이동
        photobutton.setOnClickListener {
            val intent = Intent(this, EditPhotoPage::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

    }


}