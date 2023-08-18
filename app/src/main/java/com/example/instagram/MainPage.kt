package com.example.instagram

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.FeedAdapter
import com.example.instagram.Adapter.ProfileAdapter
import com.example.instagram.Data.Comment
import com.example.instagram.Data.Userinfo
import com.example.instagram.Data.UserinfoSingleton

//        Comment("김영현", "관심일촌", R.drawable.girl1, "2023.08.16", "18조 화이팅!"),
//        Comment("추지연", "어느별에서왔니도대체", R.drawable.girl2, "2023.08.15", "■■■■■□90% 충전중"),
//        Comment("이승현", "우린 먼가 달라", R.drawable.man1, "2023.08.14", "거기 하늘라이프죠?"),
//        Comment("안주환", "너는나의엔돌핀", R.drawable.man2, "2023.08.13", "Very important person"),

class MainPage : AppCompatActivity() {

    val userinfoList = UserinfoSingleton.getUserinfoList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page_activity)

        //사용자이름 상단에 표시
        val userinfoList = UserinfoSingleton.getUserinfoList()
        val loginUserId = intent.getStringExtra("id").toString()
        val userInfo = userinfoList.find{it.id == loginUserId}
        val welcome=findViewById<TextView>(R.id.welcome)
        val welcomeMessage = if (userInfo != null) {
            "환영합니다, ${userInfo.name} 님"
        } else {
            "환영합니다, 손님"
        }
        welcome.text = welcomeMessage

        // 리사이클러뷰에 표시할 사용자 정보 가져오기
        val userProfile = UserinfoSingleton.getUserinfoList()
        Log.d("userinfo", userProfile.toString())

        val rvProfile = findViewById<RecyclerView>(R.id.rv_profile)
        val profileAdapter = ProfileAdapter(userProfile)

        rvProfile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvProfile.adapter = profileAdapter


//        //개인페이지 초기화값
//        val ibProfile1 = findViewById<ImageButton>(R.id.ib_profile1)
//        val ibProfile2 = findViewById<ImageButton>(R.id.ib_profile2)
//        val ibProfile3 = findViewById<ImageButton>(R.id.ib_profile3)
//        val ibProfile4 = findViewById<ImageButton>(R.id.ib_profile4)
//        val userinfoList = UserinfoSingleton.getUserinfoList()
//
//        imageButtonChange("김영현", ibProfile1)
//
//        ibProfile1.setOnClickListener {
//            val intent = Intent(this, MyPage::class.java)
//            val userinfo = userinfoList[1]
//
//            UserinfoSingleton.todayIncrease(userinfo)
//
//            intent.putExtra("num", 0)
//            startActivity(intent)
//        }
//
//        moveDetailPage(ibProfile3,1)

        //피드보여주기

        val jiyeonUser = userinfoList.find { it.name == "추지연" } as Userinfo
        val jiyeonCommetList = arrayListOf<Comment>(
            Comment("김영현", "관심일촌", R.drawable.girl1, "2023.08.16", "18조 화이팅!"),
            Comment("이승현", "우린 먼가 달라", R.drawable.man1, "2023.08.14", "거기 하늘라이프죠?"),
            Comment("안주환", "너는나의엔돌핀", R.drawable.man2, "2023.08.13", "Very important person"))
        UserinfoSingleton.setcommetList(jiyeonUser, jiyeonCommetList)





//        val ibProfile1 = findViewById<ImageButton>(R.id.ib_profile1)
//        val ibProfile2 = findViewById<ImageButton>(R.id.ib_profile2)
//        val ibProfile3 = findViewById<ImageButton>(R.id.ib_profile3)
//        val ibProfile4 = findViewById<ImageButton>(R.id.ib_profile4)
//


//        //김영현님 이미지 버튼 누르면 MyPage로 이동
//        ibProfile1.setOnClickListener {
//            val intent = Intent(this, MyPage::class.java)
//            val userinfo = userinfoList.find { it.name == "김영현" } as Userinfo
//
//            UserinfoSingleton.todayIncrease(userinfo)
//
//            intent.putExtra("num", 0)
//            startActivity(intent)
//        }
//
//
//        //이미지 버튼 누르면 DetailPage로 이동하는 함수
//        moveDetailPage(ibProfile3,2)


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

        }

    }
}