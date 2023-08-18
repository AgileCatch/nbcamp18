package com.example.instagram

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.FeedAdapter
import com.example.instagram.Data.Comment
import com.example.instagram.Data.Userinfo
import com.example.instagram.Data.UserinfoSingleton

class MainPage : AppCompatActivity() {

    val userinfoList = UserinfoSingleton.getUserinfoList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page_activity)

        //사용자이름 상단에 표시
        val userinfoList = UserinfoSingleton.getUserinfoList()
        val userInfo = userinfoList.firstOrNull()
        val welcome=findViewById<TextView>(R.id.welcome)
        val welcomeMessage = if (userInfo != null) {
            "환영합니다, ${userInfo.name} 님"
        } else {
            "환영합니다, 손님"
        }
        welcome.text = welcomeMessage


        //개인페이지 초기화
        UserinfoSingleton.addUserinfo(Userinfo("김영현", "yeonghyeon", R.drawable.girl1,0, "안녕하세요\n김영현 미니홈피입니다.", 100, 900, R.drawable.miniroom, "영현님의 미니룸"))
        UserinfoSingleton.addUserinfo(Userinfo("이승현", "sh4340", R.drawable.man1,0, "안녕하세요\n이승현 미니홈피입니다.", 10, 900, R.drawable.miniroom3, "지연님의 미니룸"))
        UserinfoSingleton.addUserinfo(Userinfo("추지연", "jiyeon", R.drawable.girl2,0, "안녕하세요\n추지연 미니홈피입니다.", 10, 900, R.drawable.miniroom2, "지연님의 미니룸"))
        UserinfoSingleton.addUserinfo(Userinfo("안주환", "ajh1346", R.drawable.man2,0, "안녕하세요\n안주환 미니홈피입니다.", 10, 900, R.drawable.miniroom5, "지연님의 미니룸"))

        val jiyeonUser = userinfoList.find { it.name == "추지연" } as Userinfo
        val jiyeonCommetList = arrayListOf<Comment>(
            Comment("김영현", "관심일촌", R.drawable.girl1, "2023.08.16", "18조 화이팅!"),
            Comment("이승현", "우린 먼가 달라", R.drawable.man1, "2023.08.14", "거기 하늘라이프죠?"),
            Comment("안주환", "너는나의엔돌핀", R.drawable.man2, "2023.08.13", "Very important person"))
        UserinfoSingleton.setcommetList(jiyeonUser, jiyeonCommetList)


        val ibProfile1 = findViewById<ImageButton>(R.id.ib_profile1)
        val ibProfile2 = findViewById<ImageButton>(R.id.ib_profile2)
        val ibProfile3 = findViewById<ImageButton>(R.id.ib_profile3)
        val ibProfile4 = findViewById<ImageButton>(R.id.ib_profile4)


        //EditPage에서 이미지가 변경되면 mainpage 이미지 변경하는 함수
        imageButtonChange("김영현", ibProfile1)


        //김영현님 이미지 버튼 누르면 MyPage로 이동
        ibProfile1.setOnClickListener {
            val intent = Intent(this, MyPage::class.java)
            val userinfo = userinfoList.find { it.name == "김영현" } as Userinfo

            UserinfoSingleton.todayIncrease(userinfo)

            intent.putExtra("num", 0)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }


        //이미지 버튼 누르면 DetailPage로 이동하는 함수
        moveDetailPage(ibProfile3,2)



        //피드 추가 : 넣는 순서대로 뜨기 때문에 순서를 잘 확인해야함
        val feedList = arrayListOf(
            //수정 필요
            userinfoList.find{it.name == "김영현"} as Userinfo,
            userinfoList.find{it.name == "추지연"} as Userinfo,
        )

        val rv_feed = findViewById<RecyclerView>(R.id.rv_feed)
        rv_feed.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_feed.setHasFixedSize(true)

        rv_feed.adapter = FeedAdapter(feedList, userinfoList)

    }

    //EditPage에서 이미지가 변경되면 mainpage 이미지 변경하는 함수
    fun imageButtonChange(username:String, ImgButton:ImageButton){
        var userinfo = userinfoList.find { it.name == username } as Userinfo

        if(userinfo.changedProfileImg == Uri.EMPTY){
            ImgButton.setImageResource(userinfo.profileImg)
        }else{
            ImgButton.setImageURI(userinfo.changedProfileImg)
        }
    }

    //이미지 버튼 누르면 DetailPage로 이동하는 함수
    fun moveDetailPage(Imb:ImageButton, i:Int){

        Imb.setOnClickListener {
            val intent = Intent(this, DetailPage::class.java)
            val userinfo = userinfoList[i]

            UserinfoSingleton.todayIncrease(userinfo)

            intent.putExtra("position", i)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        }

    }
}