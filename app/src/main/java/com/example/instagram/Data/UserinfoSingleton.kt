package com.example.instagram.Data

import android.net.Uri
import com.example.instagram.R

object UserinfoSingleton {
    private val userList: MutableList<Userinfo> = mutableListOf(
        Userinfo("김영현", "yeonghyeon", R.drawable.girl1,0, "안녕하세요\n김영현 미니홈피입니다.", 100, 900, R.drawable.miniroom, "영현님의 미니룸"),
        Userinfo("이승현", "sh4340", R.drawable.man1,0, "안녕하세요\n이승현 미니홈피입니다.", 10, 900, R.drawable.miniroom3, "지연님의 미니룸"),
        Userinfo("추지연", "jiyeon", R.drawable.girl2,0, "안녕하세요\n추지연 미니홈피입니다.", 10, 900, R.drawable.miniroom2, "지연님의 미니룸"),
        Userinfo("안주환", "ajh1346", R.drawable.man2,0, "안녕하세요\n안주환 미니홈피입니다.", 10, 900, R.drawable.miniroom5, "지연님의 미니룸")
    )
    var userProfile: Userinfo? = null//프로필 리사이클러뷰연결

    fun addUserinfo(userinfo: Userinfo) {
        userList.add(userinfo)
    }

    fun changeUserImg(userinfo: Userinfo, newChangedProfileImg:Uri, newChangedMiniroomImg: Uri){
        userinfo.changedProfileImg = newChangedProfileImg
        userinfo.changedMiniroomImg = newChangedMiniroomImg
    }

    fun todayIncrease(userinfo: Userinfo) {
        userinfo.today = userinfo.today + 1
    }

    fun addcommentList(userinfo: Userinfo, newComment:Comment){
        userinfo.commentList.add(0, newComment)
    }

    fun setcommetList(userinfo: Userinfo, newCommetList:ArrayList<Comment>){
        userinfo.commentList = newCommetList
    }

    fun getUserinfoList(): List<Userinfo> {
        return userList
    }
}