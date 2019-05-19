package com.example.a190306app

/*
* stationId: 대여소 id
* stationName: 대여소 이름
* rackTotCnt: 거치대 갯수
* parkingBikeTotCnt: 주차된 자전거 총 갯수(연결거치 포함)
* shared: 거치율( parkingBikeTotCnt/rackTotCnt)
* stationLatitude : 위도
* stationLongitude : 경도
* */

data class MyBike(val stationId:String, val stationName:String, val rackTotCnt:Int, val parkingBikeTotCnt:Int,
                  val shared:Int, val stationLatitude:Double, val stationLongitude:Double ) {
}
