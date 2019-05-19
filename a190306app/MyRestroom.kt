package com.example.a190306app
/*
* id: 공중화장실 id
* fName: 대명칭 ex. 건물이름
* aName: 중명칭 ex. 민간개방화장실
* c_x, c_y: 중앙좌표
* wgs84_x,wgs84_y: WGS84 좌표
* */
data class MyRestroom(val id:Int, val fName:String, val aName:String,
                      val c_x:Double,val c_y:Double,val wgs84_x:Double, val wgs84_y:Double) {
}