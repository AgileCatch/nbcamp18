package com.example.instagram

import DataManager
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.PhotoCardAdapter
import com.example.instagram.Data.PhotoCard

//사진초기화

private var selectedPhotoUri : Uri = Uri.EMPTY
class EditPhotoPage : AppCompatActivity() {


    private lateinit var photoactivityResult: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_photo_page)

        val addphotobutton = findViewById<ImageButton>(R.id.edit_add)


        photoactivityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val data: Intent? = result.data
                selectedPhotoUri = data?.data as Uri


                val addphotobutton = findViewById<ImageView>(R.id.iv_photo)
                addphotobutton.scaleType = ImageView.ScaleType.CENTER_CROP
                addphotobutton.setImageURI(selectedPhotoUri) // 이미지 업데이트
            }
        }

        addphotobutton.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            photoactivityResult.launch(intent)
        }



        //뒤로가기 버튼작동
        val backButton = findViewById<ImageButton>(R.id.imb_back)
        backButton.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        //사진첩 리사이클 뷰 연결
        val rv_photo = findViewById<RecyclerView>(R.id.rv_photo)
        rv_photo.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_photo.setHasFixedSize(true)

        // DataManager에서 사진 데이터를 가져와서 어댑터에 설정
        val photoCardList = DataManager.getPhotoCardList()
        val arrayListPhotoCardList = ArrayList(photoCardList) // List를 ArrayList로 변환
        rv_photo.adapter = PhotoCardAdapter(arrayListPhotoCardList)

        //사진 추가하기
        val button = findViewById<Button>(R.id.edit_photo_add)
        button.setOnClickListener {
            val titleInput = findViewById<EditText>(R.id.edit_title)//제목
            val contentInput = findViewById<EditText>(R.id.edit_content)//내용

            val titleText = titleInput.text.toString()
            val contentText = contentInput.text.toString()

            // 글자 수 제한 설정
            val maxIlchonLength = 10
            val maxContentLength = 100


            if (titleText.isNotEmpty() && contentText.isNotEmpty()) {
                if (titleText.length <= maxIlchonLength && contentText.length <= maxContentLength) {
                    if (selectedPhotoUri != null) { // 사진이 선택되었을 경우에만 추가
                        val newPhotoCard = PhotoCard(
                            titleText,
                            selectedPhotoUri,
                            getCurrentDate(),
                            contentText
                        )
                        DataManager.addPhotoCard(newPhotoCard) // DataManager에 사진 추가

                        showToast("사진이 등록되었습니다.")

                        // 입력 필드 비우기
                        titleInput.text.clear()
                        contentInput.text.clear()
                        addphotobutton.setImageURI(null) // 이미지 초기화
                        rv_photo.adapter?.notifyDataSetChanged() // 어댑터 갱신

                    } else {
                        showToast("사진을 선택해주세요.")
                    }
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
