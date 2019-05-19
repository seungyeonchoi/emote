package com.example.a190306app

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class dataParser(var bList:MutableList<MyBike>,var dList:MutableList<MyDust>,var rList:MutableList<MyRestroom>,var pList:MutableList<MyPark>){
//json데이터 파싱하는 클래스
    /*
    * 고민사항
    * api마다 따로 파싱하는 클래스를 둘지/ 현 방식대로 할지
    * 따로 두면 일단 클래스가 많아져서 복잡해보임 . ..
    * 기존 방식은 통합적으로 관리하기때문에 한번에 하려해서 복잡해짐 ㅠㅠㅠ
    * */
    enum class Data(val type: Int) {
        BIKE(0),
        DUST(1),
        RESTROOM(2),
        PARK(3)
    }

    fun parse(type: Int, jsonString: String) {

        when (type) {
            Data.BIKE.type ->bikeParse(jsonString)
            Data.DUST.type ->dustParse(jsonString)
            Data.RESTROOM.type ->restroomParse(jsonString)
            Data.PARK.type ->parkParse(jsonString)
        }
    }
    fun bikeParse(jsonString: String){
        try {
            var jarray: JSONArray = JSONObject(jsonString).getJSONObject("rentBikeStatus").getJSONArray("row")
            for (i in 0..jarray.length()) {
                var jObject = jarray.getJSONObject(i)
                var stationId: String = jObject.optString("stationId")

                var stationName: String = jObject.optString("stationName")
                var rackTotCnt: Int = jObject.optInt("rackTotCnt")
                var parkingBikeTotCnt: Int = jObject.optInt("parkingBikeTotCnt")
                var shared: Int = jObject.optInt("shared")
                var stationLatitude: Double = jObject.optDouble("stationLatitude")
                var stationLongitude: Double = jObject.optDouble("stationLongitude")
                bList.add(
                    MyBike(
                        stationId,
                        stationName,
                        rackTotCnt,
                        parkingBikeTotCnt,
                        shared,
                        stationLatitude,
                        stationLongitude
                    )
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
    fun dustParse(jsonString: String){
        //미세먼지는 위치정보 받아오면, 그 위치가 속한 구를 url에 붙여서 파싱해야하는데, 아직은 없으므로 row로 지정
        try {
            var jarray: JSONArray = JSONObject(jsonString).getJSONObject("RealtimeCityAir").getJSONArray("row")
            for (i in 0..jarray.length()) {
                var jObject = jarray.getJSONObject(i)
                var pm10: Double = jObject.optDouble("PM10")
                var pm25: Double = jObject.optDouble("PM25")
                var idex_nm: String = jObject.optString("IDEX_NM")
                var idex_mvl: Double = jObject.optDouble("IDEX_MVL")
                dList.add(
                    MyDust(pm10,pm25,idex_nm,idex_mvl)
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
    fun restroomParse(jsonString: String){
        try {
            var jarray: JSONArray = JSONObject(jsonString).getJSONObject("SearchPublicToiletPOIService").getJSONArray("row")
            for (i in 0..jarray.length()) {
                var jObject = jarray.getJSONObject(i)
                var id: Int = jObject.optInt("POI_ID")
                var fName: String = jObject.optString("FNAME")
                var aName: String = jObject.optString("ANAME")
                var c_x: Double = jObject.optDouble("CENTER_X1")
                var c_y: Double = jObject.optDouble("CENTER_Y1")
                var wgs84_x: Double = jObject.optDouble("X_WGS84")
                var wgs84_y: Double = jObject.optDouble("Y_WGS84")
                rList.add(
                    MyRestroom(id,fName,aName,c_x,c_y,wgs84_x,wgs84_y)
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
    fun parkParse(jsonString: String){
        // 공원은 제공하는 정보가 많아서, 다같이 필요한 데이터 확인할 필요 있을 듯
        try {
            var jarray: JSONArray = JSONObject(jsonString).getJSONObject("SearchParkInfoService").getJSONArray("row")
            for (i in 0..jarray.length()) {
                var jObject = jarray.getJSONObject(i)
                var id: Int = jObject.optInt("P_IDX")
                var name: String = jObject.optString("P_NAME")
                var zone: String = jObject.optString("P_ZONE")
                var addr: String = jObject.optString("P_ADDR")
                var g_longitude: Double = jObject.optDouble("G_LONGITUDE")
                var g_latitude: Double = jObject.optDouble("G_LATITUDE")
                var longitude: Double = jObject.optDouble("LONGITUDE")
                var latitude: Double = jObject.optDouble("LATITUDE")
                pList.add(
                    MyPark(id,name,zone,addr,g_longitude,g_latitude,longitude,latitude)
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()

        }
    }
    fun getList(type: Int):MutableList<Any>?{
        when (type) {
            Data.BIKE.type ->return bList as MutableList<Any>
            Data.DUST.type ->return dList as MutableList<Any>
            Data.RESTROOM.type ->return rList as MutableList<Any>
            Data.PARK.type ->return pList as MutableList<Any>
            else -> return null
        }

    }
}