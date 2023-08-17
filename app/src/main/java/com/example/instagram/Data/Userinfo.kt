package com.example.instagram.Data


class Userinfo {
    var name:String = ""
    var id:String = ""
    var profileImg:Int = 0
    var today:Int = 0
    var description:String = ""
    var ilchon:Int = 0
    var favorites:Int = 0
    var miniroom:Int = 0
    var roomname:String = ""


    constructor(_name:String, _id:String, _profileImg:Int, _today:Int, _description:String, _ilchon:Int, _favorites:Int, _miniroom:Int, _roomname:String){
        name = _name
        id = _id
        profileImg = _profileImg
        today = _today
        description = _description
        ilchon = _ilchon
        favorites = _favorites
        miniroom = _miniroom
        roomname = _roomname
    }

    constructor(feedId:String, feedProfile:Int, feedImg:Int, feedText:String){

        id = feedId
        profileImg = feedProfile
        miniroom = feedImg
        description = feedText

    }

}
