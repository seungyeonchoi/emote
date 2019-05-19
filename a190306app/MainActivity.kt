package com.example.a190306app

//190306, 190313 ( 버튼달기)


import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    var urlStr = arrayOf(
        "http://openapi.seoul.go.kr:8088/746c776f61627a7437376b49567a68/json/bikeList/1/15/", //대여소 1525개 있음 , 1000씩 나눠서 호출해야함
        "http://openapi.seoul.go.kr:8088/6d71556a42627a7437377549426e67/json/RealtimeCityAir/1/15/",
        "http://openapi.seoul.go.kr:8088/694b534943627a7434307364586868/json/SearchPublicToiletPOIService/1/5/",
        "http://openapi.seoul.go.kr:8088/527a4a4b47627a74363558734a7658/json/SearchParkInfoService/1/15/"
    )

    var bList = mutableListOf<MyBike>()
    var dList = mutableListOf<MyDust>()
    var rList = mutableListOf<MyRestroom>()
    var pList = mutableListOf<MyPark>()
    var dParse = dataParser(bList, dList, rList, pList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //액티비티 메인에 있는 함수들을 전부 인스턴스화 시킴

        init()
    }

    fun init() {
        button4.setOnClickListener(this) //방식 2: button4를 누르면 메인 엑티비티의 온클릭함수로 전달, 어떤 버튼이든 this만 달리면 호출 가능함, 공용으로 인터페이스를 달아주는 방식
        //imageButton.setOnClickListener() //1) 핸들러클래스 작성 후 그 핸들러클래스의 객체를 넣어주는 방법 //2) 묵시적 객체를 생략하고 람다함수 형식으로 처리하는 방식(익명함수)
        imageButton.setOnClickListener {
            //방식 3: 이미지버튼만 사용가능한 버튼, 전용 인터페이스를 달아주는 방식
            Toast.makeText(this, "이미지버튼 클릭", Toast.LENGTH_LONG).show()
        }

        //api url 연결해서 데이터 받아와서 파싱하여 리스트로 저장
        val networkTask0 = NetworkTask(textView2, 0, urlStr[0], dParse)
        networkTask0.execute()
        val networkTask1 = NetworkTask(textView2, 1, urlStr[1], dParse)
        networkTask1.execute()
        val networkTask2 = NetworkTask(textView2, 2, urlStr[2], dParse)
        networkTask2.execute()
        val networkTask3 = NetworkTask(textView2, 3, urlStr[3], dParse)
        networkTask3.execute()

        //api 데이터 값 쓰려면 dParse.bList(dList / rList/ pList) 로 접근하면 됨!
    }


    override fun onClick(v: View?) { //여러개 공유할 떄 쓰는 방식 : onClick 공용함수

        when (v?.id) {
            R.id.button4 -> {
                Toast.makeText(this, "버튼 2 클릭", Toast.LENGTH_LONG).show()
            }
            R.id.imageButton -> {
                Toast.makeText(this, "이미지 클릭", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun btnClick(v: View?): Unit { //View 타입이고 null값이 들어올 수 있고 반환값은 없음
        Toast.makeText(this, "버튼 1 클릭", Toast.LENGTH_LONG).show()
    } //방식1 : 특정함수 만들어서 직접 연결해주는 방식, 함수가 미리 있어야함

    class NetworkTask(val tview: TextView, val type: Int, val url: String, val dParse: dataParser) :
        AsyncTask<Unit, Unit, String>() { //void 대신 unit

        enum class Data(val type: Int) {
            BIKE(0),
            DUST(1),
            RESTROOM(2),
            PARK(3)
        }


        override fun doInBackground(vararg params: Unit?): String {
            val result: String // 요청 결과를 저장할 변수.
            val requestHttpURLConnection = RequestHttpURLConnection()
            result = requestHttpURLConnection.request(url) // 해당 URL로 부터 결과물을 얻어온다.
            return result
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            //tview.text = result
            dParse.parse(type, result!!)
//
//            when (type) {
//                Data.BIKE.type ->{
//                    Log.i("asdf",dParse.bList.first().stationName)
//                }
//                Data.DUST.type ->{
//                    var list= dParse.getList(type) as MutableList<MyDust>
//                    Log.i("asdf",list.first().idex_nm)
//                }
//                Data.RESTROOM.type ->{
//                    var list= dParse.getList(type) as MutableList<MyRestroom>
//                    Log.i("asdf",list.first().fName)
//                }
//                Data.PARK.type ->{
//                    var list= dParse.getList(type) as MutableList<MyPark>
//                    Log.i("asdf",list.first().name)
//                }
//            }


        }
    }
}

