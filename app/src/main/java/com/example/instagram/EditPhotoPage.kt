package com.example.instagram

import DataManager
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.PhotoCardAdapter
import com.example.instagram.Data.PhotoCard

class EditPhotoPage : AppCompatActivity() {

    private lateinit var photoActivityResult: ActivityResultLauncher<Intent>
    private lateinit var selectedPhotoUri: Uri

    companion object {
        private const val PERMISSION_REQUEST_CODE = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_photo_page)

        val photoButton = findViewById<ImageButton>(R.id.imb_photo)
        val addButton = findViewById<Button>(R.id.edit_photo_add)
        val rv_photocard = findViewById<RecyclerView>(R.id.rv_photo)

        photoActivityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val data: Intent? = result.data
                selectedPhotoUri = data?.data as Uri

                photoButton.setImageURI(selectedPhotoUri)
                photoButton.scaleType = ImageView.ScaleType.CENTER_CROP
            }
        }

        photoButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.type = "image/*"
                photoActivityResult.launch(intent)
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_CODE
                )
            }
        }

        val photoCardList = DataManager.getPhotoCardList()

        rv_photocard.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_photocard.setHasFixedSize(true)

        rv_photocard.adapter = PhotoCardAdapter(photoCardList)

        addButton.setOnClickListener {
            val titleInput = findViewById<EditText>(R.id.edit_title)//제목
            val contentInput = findViewById<EditText>(R.id.edit_content)//내용

            val titleText = titleInput.text.toString()
            val contentText = contentInput.text.toString()

            // 글자 수 제한 설정
            val maxIlchonLength = 10
            val maxContentLength = 100

            if (titleText.isNotEmpty() && contentText.isNotEmpty()) {
                if (titleText.length <= maxIlchonLength && contentText.length <= maxContentLength) {
                    if (::selectedPhotoUri.isInitialized) {
                        val newPhotoCard = PhotoCard(
                            titleText,
                            selectedPhotoUri,
                            getCurrentDate(),
                            contentText
                        )

                        DataManager.addPhotoCard(newPhotoCard)
                        rv_photocard.adapter?.notifyDataSetChanged()

                        showToast("사진이 등록되었습니다.")

                        // 입력 필드 비우기
                        titleInput.text.clear()
                        contentInput.text.clear()
                        photoButton.setImageURI(null) // 이미지 초기화

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

        //뒤로가기 버튼작동
        val backButton = findViewById<ImageButton>(R.id.imb_back)
        backButton.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
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
