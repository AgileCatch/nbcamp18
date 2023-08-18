package com.example.instagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SIgnInPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_page)

        //회원가입버튼 클릭시 동작
        val signUpBtn = findViewById<Button>(R.id.signUpBtn)
        signUpBtn.setOnClickListener {
            val intent = Intent(this, SIgnUpPage::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        }

        //로그인 버튼 클릭 시 동작
        val logInBtn = findViewById<Button>(R.id.logInBtn)
        logInBtn.setOnClickListener {
            // 입력한 이메일과 비밀번호 가져오기
            val emailInput = findViewById<EditText>(R.id.emailID)
            val passwordInput = findViewById<EditText>(R.id.pw)
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            // 이메일과 비밀번호 검증
            val savedEmail = intent.getStringExtra("email")
            val savedPassWord = intent.getStringExtra("password")

            if (email == savedEmail && password == savedPassWord) {
                Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                val mainPageIntent = Intent(this, MainPage::class.java)
                mainPageIntent.putExtra("id", email)
                startActivity(mainPageIntent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                finish()
            } else {
                Toast.makeText(this, "이메일/비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show()
            }
        }
        val emailData = intent.getStringExtra("email")
        val editTextEmail = findViewById<EditText>(R.id.emailID)
        editTextEmail.setText(emailData)

        val passWordData = intent.getStringExtra("password")
        val editTextPassWord = findViewById<EditText>(R.id.pw)
        editTextPassWord.setText(passWordData)
    }
}
