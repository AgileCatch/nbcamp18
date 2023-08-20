package com.example.instagram

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Adapter.FeedAdapter
import com.example.instagram.Adapter.ProfileAdapter
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
        val userInfo = userinfoList[0]
        val welcome = findViewById<TextView>(R.id.welcome)
        val welcomeMessage = if (userInfo != null) {
            "환영합니다, ${userInfo.name} 님"
        } else {
            "환영합니다, 손님"
        }
        welcome.text = welcomeMessage

        //피드보여주기
        val jiyeonUser = userinfoList.find { it.name == "추지연" } as Userinfo
        val jiyeonCommetList = arrayListOf<Comment>(
            Comment("김영현", "관심일촌", R.drawable.girl1, "2023.08.16", "18조 화이팅!"),
            Comment("안주환", "너는나의엔돌핀", R.drawable.man2, "2023.08.13", "Very important person")
        )
        UserinfoSingleton.setcommetList(jiyeonUser, jiyeonCommetList)

        val yeonghyeonUser = userinfoList.find { it.name == "김영현" } as Userinfo
        val yeonghyeonCommetList = arrayListOf<Comment>(
            Comment("추지연", "어느별에서왔니도대체", R.drawable.girl2, "2023.08.15", "■■■■■□90% 충전중"),
            Comment("이승현", "우린 먼가 달라", R.drawable.man1, "2023.08.14", "거기 하늘라이프죠?")
        )
        UserinfoSingleton.setcommetList(yeonghyeonUser, yeonghyeonCommetList)


        //피드 추가 : 넣는 순서대로 뜨기 때문에 순서를 잘 확인해야함
        val profileList = arrayListOf(
            userinfoList.find { it.name == "김영현" } as Userinfo,
            userinfoList.find { it.name == "이승현" } as Userinfo,
            userinfoList.find { it.name == "추지연" } as Userinfo,
            userinfoList.find { it.name == "안주환" } as Userinfo,
        )

        if (UserinfoSingleton.getUserinfoList().size > 4) {
            val userinfoList = UserinfoSingleton.getUserinfoList()

            for (i in 0 until userinfoList.size - 4) {
                val userinfo = userinfoList[i]
                profileList.add(0, userinfo)
            }
        }

        // 리사이클러뷰에 표시할 사용자 정보 가져오기
        val userProfile = UserinfoSingleton.getUserinfoList()
        Log.d("userinfo", userProfile.toString())

        val rvProfile = findViewById<RecyclerView>(R.id.rv_profile)
        val profileAdapter = ProfileAdapter(profileList)

        rvProfile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvProfile.adapter = profileAdapter

        val rv_feed = findViewById<RecyclerView>(R.id.rv_feed)
        rv_feed.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_feed.setHasFixedSize(true)

        rv_feed.adapter = FeedAdapter(profileList)

    }

}