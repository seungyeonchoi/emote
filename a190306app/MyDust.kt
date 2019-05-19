package com.example.a190306app
/*
* pm10: 미세먼지 농도
* pm25: 초미세먼지 농도
* idex_nm: 통합대기환경등급 ex. 좋음, 보통 , ...
* idex_mval: 통합대기환경지수
* */
data class MyDust(val pm10:Double, val pm25:Double, val idex_nm:String,val idex_mvl:Double) {
}