package com.example.instagram.Data

import android.net.Uri

object UserinfoSingleton {
    private val userList: MutableList<Userinfo> = mutableListOf()
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

    fun getUserinfoList(): List<Userinfo> {
        return userList
    }
}