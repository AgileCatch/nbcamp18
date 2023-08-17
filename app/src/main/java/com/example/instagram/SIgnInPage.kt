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

        val signUpBtn = findViewById<Button>(R.id.signUpBtn)
        signUpBtn.setOnClickListener{
            val intent = Intent(this, SIgnUpPage::class.java)
            startActivity(intent)
        }

        val logInBtn = findViewById<Button>(R.id.logInBtn)
        logInBtn.setOnClickListener {
            val emailInput = findViewById<EditText>(R.id.emailID)
            val passwordInput = findViewById<EditText>(R.id.pw)

            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            val savedEmail = intent.getStringExtra("email")
            val savedPassWord = intent.getStringExtra("password")


            if (email == savedEmail && password == savedPassWord) {
                Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                val mainPageIntent = Intent(this, MainPage::class.java)
                startActivity(mainPageIntent)
                finish()
            } else {

                Toast.makeText(this, "이메일/비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
