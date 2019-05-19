package com.example.a190306app
/*
* id: 공원 id
* name: 공원 이름
* zone: 위치한 지역구
* addr: 공원 주소
* g_longitude, g_latitude: X, Y좌표(GRS80TM)
* longitude, latitude: X, Y좌표(WGS84)
* */
data class MyPark(val id:Int, val name:String,val zone:String, val addr:String, val g_longitude:Double, val g_latitude:Double, val longitude:Double, val latitude:Double) {
}