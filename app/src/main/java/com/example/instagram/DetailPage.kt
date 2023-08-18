package com.example.instagram

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.CommentAdapter
import com.example.instagram.Data.Comment
import com.example.instagram.Data.UserinfoSingleton


class DetailPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage)

        val toolbarName = findViewById<TextView>(R.id.rightText)
        val dName = findViewById<TextView>(R.id.tv_name)
        val dProfileImg = findViewById<ImageView>(R.id.rightImageView)
        val dProfileImgSquare = findViewById<ImageView>(R.id.imb_profile)
        val dtoday = findViewById<TextView>(R.id.tv_today_num)
        val dintroduce = findViewById<TextView>(R.id.tv_description)
        val dIlchon = findViewById<TextView>(R.id.tv_1chon_num)
        val dFavorites = findViewById<TextView>(R.id.tv_favorites_num)
        val dMiniroom = findViewById<ImageView>(R.id.iv_miniroom)
        val dRoomname = findViewById<TextView>(R.id.roomname)

        val commentButton = findViewById<Button>(R.id.button)
        val Etilchon = findViewById<EditText>(R.id.et_ilchon)
        val Etcontent = findViewById<EditText>(R.id.et_content)

        //뒤로가기 버튼작동
        val backButton = findViewById<ImageButton>(R.id.imb_back)
        backButton.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        //사진첩으로 화면전환
        val bt_photo = findViewById<Button>(R.id.bt_photo)
        bt_photo.setOnClickListener {
            val intent = Intent(this, PhotoAlbumPage::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }


        //Intent로 데이터 가져오기
        var num = intent.getIntExtra("position", 0)
        val userinfoList = UserinfoSingleton.getUserinfoList()
        val userinfo = userinfoList[num]

        toolbarName.text = "${userinfo.name}의 미니홈피"
        dName.text = userinfo.name
        dProfileImg.setImageResource(userinfo.profileImg)
        dProfileImgSquare.setImageResource(userinfo.profileImg)
        dtoday.text = userinfo.today.toString()
        dintroduce.text = userinfo.description
        dIlchon.text = userinfo.ilchon.toString()
        dFavorites.text = userinfo.favorites.toString()
        dMiniroom.setImageResource(userinfo.miniroom)
        dRoomname.text = userinfo.roomname

        //댓글 리사이클 뷰 연결
        val rv_comment = findViewById<RecyclerView>(R.id.rv_comment)
        rv_comment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_comment.setHasFixedSize(true)

        rv_comment.adapter = CommentAdapter(userinfo.commentList)


        //일촌평 추가하기
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val ilchonInput = findViewById<EditText>(R.id.et_ilchon)//일촌명
            val contentInput = findViewById<EditText>(R.id.et_content)//일촌평

            val ilchonText = ilchonInput.text.toString()
            val contentText = contentInput.text.toString()

            // 글자 수 제한 설정
            val maxIlchonLength = 10
            val maxContentLength = 100

            //댓글처리
            if (ilchonText.isNotEmpty() && contentText.isNotEmpty()) {
                if (ilchonText.length <= maxIlchonLength && contentText.length <= maxContentLength) {
                    val newComment =
                        Comment(
                            "작성자 이름",
                            ilchonText,
                            R.drawable.man1,
                            getCurrentDate(),
                            contentText
                        )
                    UserinfoSingleton.addcommentList(userinfo, newComment)
                    rv_comment.adapter?.notifyItemInserted(userinfo.commentList.size - 1)

                    showToast("일촌평이 등록 되었습니다.")

                    // 입력 필드 비우기
                    ilchonInput.text.clear()
                    contentInput.text.clear()
                } else {
                    if (ilchonText.length > maxIlchonLength) {
                        // 일촌명 글자 제한 초과 팝업 메시지
                        showToast("일촌명은 ${maxIlchonLength}자 이하로 입력해주세요.")
                    }

                    if (contentText.length > maxContentLength) {
                        // 일촌평 글자 제한 초과 팝업 메시지
                        showToast("일촌평은 ${maxContentLength}자 이하로 입력해주세요.")
                    }
                }
            } else {
                showToast("비어있는 칸이 있습니다.")
            }
        }
    }

    // 현재 날짜를 가져오는 함수
    private fun getCurrentDate(): String {
        val currentDate = java.text.SimpleDateFormat("yyyy.MM.dd", java.util.Locale.getDefault())
            .format(java.util.Date())
        return currentDate
    }

    // 팝업 메시지 표시 함수
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}