package com.example.instagram

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SIgnUpPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)

        val signUpBtn2 = findViewById<Button>(R.id.signUpBtn2)
        val newEmailId = findViewById<EditText>(R.id.newEmailId)
        val newPW = findViewById<EditText>(R.id.newPW)
        val newNickName = findViewById<EditText>(R.id.newNickName)
        val newName = findViewById<EditText>(R.id.NewName)




        signUpBtn2.setOnClickListener {
            val email = newEmailId.text.toString()
            val password = newPW.text.toString()
            val nickname = newNickName.text.toString()
            val name = newName.text.toString()




            val signInPageIntent = Intent(this, SIgnInPage::class.java)
            signInPageIntent.putExtra("email", email)
            signInPageIntent.putExtra("password", password)
            signInPageIntent.putExtra("nickname", nickname)
            signInPageIntent.putExtra("name", name)
            startActivity(signInPageIntent)

            val mainPageIntent = Intent(this, MainPage::class.java)
            signInPageIntent.putExtra("email", email)
            signInPageIntent.putExtra("password", password)
            signInPageIntent.putExtra("nickname", nickname)
            signInPageIntent.putExtra("name", name)
            startActivity(mainPageIntent)




            finish()



    }

}}

