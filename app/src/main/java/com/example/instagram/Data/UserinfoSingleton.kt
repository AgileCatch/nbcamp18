package com.example.instagram.Data

import android.net.Uri
import com.example.instagram.Data.Userinfo

object UserinfoSingleton {
    private val userList: MutableList<Userinfo> = mutableListOf()

    fun addUserinfo(userinfo: Userinfo) {
        userList.add(userinfo)
    }

    fun changeUserImg(userinfo: Userinfo, newChangedProfileImg:Uri, newChangedMiniroomImg: Uri){
        userinfo.changedProfileImg = newChangedProfileImg
        userinfo.changedMiniroomImg = newChangedMiniroomImg
    }

    fun todayIncrease(userinfo: Userinfo){
        userinfo.today = userinfo.today + 1
    }

    fun getUserinfoList(): List<Userinfo> {
        return userList
    }
}