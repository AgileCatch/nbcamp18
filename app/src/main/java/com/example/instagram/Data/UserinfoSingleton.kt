package com.example.instagram.Data

import android.net.Uri

object UserinfoSingleton {
    private val userList: MutableList<Userinfo> = mutableListOf()

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
        userinfo.commentList.add(newComment)
    }

    fun setcommetList(userinfo: Userinfo, newCommetList:ArrayList<Comment>){
        userinfo.commentList = newCommetList
    }

    fun getUserinfoList(): List<Userinfo> {
        return userList
    }
}