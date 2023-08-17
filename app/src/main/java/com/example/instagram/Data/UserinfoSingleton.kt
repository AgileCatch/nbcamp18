package com.example.instagram.Data

import com.example.instagram.Data.Userinfo

object UserinfoSingleton {
    private val userList: MutableList<Userinfo> = mutableListOf()

    fun addUserinfo(userinfo: Userinfo) {
        userList.add(userinfo)
    }


    fun updateUserinfo(userinfo: Userinfo, newName: String, newId: String, newProfileImg: Int, newToday: Int, newDescription: String, newIlchon: Int, newFavorites: Int, newMiniroom: Int, newRoomname:String) {
        userinfo.name = newName
        userinfo.id = newId
        userinfo.profileImg = newProfileImg
        userinfo.today = newToday
        userinfo.description = newDescription
        userinfo.ilchon = newIlchon
        userinfo.favorites = newFavorites
        userinfo.miniroom = newMiniroom
        userinfo.roomname = newRoomname
    }

    fun getUserinfoList(): List<Userinfo> {
        return userList
    }
}