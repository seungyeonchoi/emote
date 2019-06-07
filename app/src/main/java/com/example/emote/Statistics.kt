package com.example.emote


import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_statistics.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Statistics : Fragment() {

    interface OnFragmentInteractionListener{

    }
    var emoArrSize:Int = 5
    var emoMaxSize:Int = 3
    var emotionArr = Array<Array<String>>(emoArrSize){Array<String>(emoMaxSize){""}} // 5일간의 감정 정보 3개
    var emotionRatio = Array<Array<Int>>(emoArrSize){Array<Int>(emoMaxSize){0}} // 5일간의 감정 정보들의 퍼센테이지
    var emotionScore = Array<Int>(emoArrSize){100}    // 5일간의 감정 점수
    var topEmoArr = Array<String>(emoArrSize){""}   // 날짜 별로 퍼센트가 제일 높은 감정
    var emoView = arrayOf(
        stat_emo1,stat_emo2,stat_emo3,stat_emo4,stat_emo5
    )
    lateinit var bitmap:Bitmap
    lateinit var canvas:Canvas


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        calculateEmo()
        drawOnCanvas()
    }
    fun drawOnCanvas(){
        bitmap=Bitmap.createBitmap(960,800,Bitmap.Config.ARGB_8888)
        canvas= Canvas(bitmap)
        canvas.drawColor(Color.BLACK)
        val paint= Paint()
        canvas.drawLine(200f,499f,119f,129f,paint)


    }
    fun calculateEmo(){
        for(i in 0 until emoArrSize){
            var max:Int = 0
            //var topEmo:String = ""
            for(j in 0 until emoMaxSize) {
                emotionScore[i] += when (emotionArr[i][j]) {
                    "" -> emotionRatio[i][j]    // 긍정적인 감정
                    else -> -emotionRatio[i][j] // 부정적인 감정
                }
                if (emotionRatio[i][j] > max) {
                    topEmoArr[i] = emotionArr[i][j]
                    max = emotionRatio[i][j]
                }
            }
            stat_emo1.setImageResource(R.drawable.icon_1_b)
            stat_emo2.setImageResource(R.drawable.icon_1_w)
            stat_emo3.setImageResource(R.drawable.icon_2_b)
            stat_emo4.setImageResource(R.drawable.icon_2_w)
            stat_emo5.setImageResource(R.drawable.icon_3_b)
           // emoView[i].setImageResource(R.drawable.icon_1_b)
        }

        for(i in 0..3){
            // emotionScore[i]부터 emotionScore[i+1]까지 선 긋기
        }

    }
}
