package com.example.instagram

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.Data.Userinfo
import com.example.instagram.Data.UserinfoSingleton


class SIgnUpPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)

        val signUpBtn2 = findViewById<Button>(R.id.signUpBtn2)
        val newEmailId = findViewById<EditText>(R.id.newEmailId)
        val newPW = findViewById<EditText>(R.id.newPW)
        val newNickName = findViewById<EditText>(R.id.newNickName)
        val newName = findViewById<EditText>(R.id.NewName)

        //새로운 사용자 정보 생성


        signUpBtn2.setOnClickListener {
            val email = newEmailId.text.toString()
            val password = newPW.text.toString()
            val nickname = newNickName.text.toString()
            val name = newName.text.toString()



            if (email.isBlank() || password.isBlank() || nickname.isBlank() || name.isBlank()) {
                Toast.makeText(this, getString(R.string.informationMessage), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.signupMessage), Toast.LENGTH_SHORT).show()


                // 새로운 사용자 정보 생성
                val newUserinfo = Userinfo(
                    name,
                    nickname,
                    R.drawable.newprofile,
                    0,
                    "",
                    0,
                    0,
                    R.drawable.empty1,
                    ""
                )
                // 생성한 사용자 정보를 UserinfoSingleton에 추가
                UserinfoSingleton.addUserinfo(newUserinfo)


                //다음화면으로 이동하는 코드
                val signInPageIntent = Intent(this, SIgnInPage::class.java)
                signInPageIntent.putExtra("email", email)
                signInPageIntent.putExtra("password", password)
                signInPageIntent.putExtra("nickname", nickname)
                signInPageIntent.putExtra("name", name)
                startActivity(signInPageIntent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

        }

    }
}

