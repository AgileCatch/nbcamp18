package com.example.instagram
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

//댓글 초기화
val commentList = arrayListOf(
    Comment("김영현", "관심일촌", R.drawable.girl1, "2023.08.16", "18조 화이팅!"),
    Comment("추지연", "어느별에서왔니도대체", R.drawable.girl2, "2023.08.15", "■■■■■□90% 충전중"),
    Comment("이승현", "우린 먼가 달라", R.drawable.man1, "2023.08.14", "거기 하늘라이프죠?"),
    Comment("안주환", "너는나의엔돌핀", R.drawable.man2, "2023.08.13", "Very important person"),)

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

        //뒤로가기 버튼작동
        val backButton = findViewById<ImageButton>(R.id.imb_back)
        backButton.setOnClickListener {
            finish()
        }

        //댓글 리사이클 뷰 연결
        val rv_comment = findViewById<RecyclerView>(R.id.rv_comment)
        rv_comment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_comment.setHasFixedSize(true)

        rv_comment.adapter = CommentAdapter(commentList)


        //Intent로 데이터 가져오기
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
                        Comment("작성자 이름", ilchonText, R.drawable.girl2, getCurrentDate(), contentText)
                    commentList.add(newComment)
                    rv_comment.adapter?.notifyItemInserted(commentList.size - 1)

                    showToast("일촌평이 등록 되었습니다.")

                    // 입력 필드 비우기
                    ilchonInput.text.clear()
                    contentInput.text.clear()
                }else {
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
        val currentDate = java.text.SimpleDateFormat("yyyy.MM.dd", java.util.Locale.getDefault()).format(java.util.Date())
        return currentDate
    }

    // 팝업 메시지 표시 함수
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}