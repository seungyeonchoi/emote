package com.example.emote
/*
* pid:전체 post중 번호: 기본값 -1 , 이후 db에 넣을 때? 바꿈
*
* */
data class Post(var pid:String, var uid:String, var date:String, var pb:String,var title:String,
                var contents:String, var emotionValue:ArrayList<Int>,  var place:String, var activity:String, var heartCount:Int=0) {

}