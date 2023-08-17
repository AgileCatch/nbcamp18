package com.example.instagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.PhotoCardAdapter
import com.example.instagram.Data.PhotoCard

//사진초기화
val photocardList = arrayListOf(
    PhotoCard("오늘도!", R.drawable.girl1, "2023.08.16", "18조 화이팅!"),
    PhotoCard("추지연", R.drawable.girl2, "2023.08.15", "■■■■■□90% 충전중"),
    PhotoCard("이승현", R.drawable.man1, "2023.08.14", "거기 하늘라이프죠?"),
    PhotoCard("안주환", R.drawable.man2, "2023.08.13", "Very important person"),
)

class EditPhotoPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_photo_page)

        //뒤로가기 버튼작동
        val backButton = findViewById<ImageButton>(R.id.imb_back)
        backButton.setOnClickListener {
            finish()
        }

        //사진첩 리사이클 뷰 연결
        val rv_photo = findViewById<RecyclerView>(R.id.rv_photo)
        rv_photo.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_photo.setHasFixedSize(true)

        rv_photo.adapter = PhotoCardAdapter(photocardList)

        //사진 추가하기
        val button = findViewById<Button>(R.id.edit_photo_add)
        button.setOnClickListener {
            val titleInput = findViewById<EditText>(R.id.edit_title)//일촌명
            val contentInput = findViewById<EditText>(R.id.edit_content)//일촌평

            val titleText = titleInput.text.toString()
            val contentText = contentInput.text.toString()

            // 글자 수 제한 설정
            val maxIlchonLength = 10
            val maxContentLength = 100


            if (titleText.isNotEmpty() && contentText.isNotEmpty()) {
                if (titleText.length <= maxIlchonLength && contentText.length <= maxContentLength) {
                    val newPhotoCard =
                        PhotoCard(
                            titleText,
                            R.drawable.girl2,
                            getCurrentDate(),
                            contentText
                        )
                    photocardList.add(newPhotoCard)
                    rv_photo.adapter?.notifyItemInserted(photocardList.size - 1)

                    showToast("사진이 등록되었습니다.")

                    // 입력 필드 비우기
                    titleInput.text.clear()
                    contentInput.text.clear()
                } else {
                    if (titleText.length > maxIlchonLength) {
                        // 타이틀 글자 제한 초과 팝업 메시지
                        showToast("제목은 ${maxIlchonLength}자 이하로 입력해주세요.")
                    }

                    if (contentText.length > maxContentLength) {
                        // 내용 글자 제한 초과 팝업 메시지
                        showToast("글은 ${maxContentLength}자 이하로 입력해주세요.")
                    }
                }
            } else {
                showToast("비어있는 칸이 있습니다.")
            }
        }
    }

    // 현재 날짜를 가져오는 함수
    private fun getCurrentDate(): String {
        val currentDate = java.text.SimpleDateFormat("yyyy/MM/dd", java.util.Locale.getDefault())
            .format(java.util.Date())
        return currentDate
    }

    // 팝업 메시지 표시 함수
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
